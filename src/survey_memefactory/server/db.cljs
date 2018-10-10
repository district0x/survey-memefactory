(ns survey-memefactory.server.db
  (:require
    [district.server.config :refer [config]]
    [district.server.db :as db]
    [district.server.db.column-types :refer [address not-nil default-nil default-zero default-false sha3-hash primary-key]]
    [district.server.db.honeysql-extensions]
    [honeysql.core :as sql]
    [honeysql.helpers :refer [merge-where merge-order-by merge-left-join defhelper]]
    [mount.core :as mount :refer [defstate]]
    [taoensso.timbre :as logging :refer-macros [info warn error]]
    [medley.core :as medley]))

(declare start)
(declare stop)
(defstate ^{:on-reload :noop} survey-memefactory-db
  :start (start (merge (:survey-memefactory/db @config)
                       (:survey-memefactory/db (mount/args))))
  :stop (stop))

(def votes-columns
  [[:vote/voter :unsigned :integer not-nil]
   [:vote/survey address not-nil]
   [:vote/survey-id :unsigned :integer not-nil]
   [:vote/option :unsigned :integer not-nil]
   [:vote/stake :unsigned :BIG :INT not-nil]
   [(sql/call :primary-key :vote/voter :vote/survey :vote/survey-id :vote/option)]])

(def votes-column-names (filter keyword? (map first votes-columns)))

(defn- index-name [col-name]
  (keyword (namespace col-name) (str (name col-name) "-index")))

(defn start [opts]
  (db/run! {:create-table [:votes]
            :with-columns [votes-columns]}))

(defn stop []
  (db/run! {:drop-table [:votes]}))

(defn create-insert-fn [table-name column-names & [{:keys [:insert-or-replace?]}]]
  (fn [item]
    (let [item (select-keys item column-names)]
      (db/run! {(if insert-or-replace? :insert-or-replace-into :insert-into) table-name
                :columns (keys item)
                :values [(vals item)]}))))

(defn create-update-fn [table-name column-names id-keys]
  (fn [item]
    (let [item (select-keys item column-names)
          id-keys (if (sequential? id-keys) id-keys [id-keys])]
      (db/run! {:update table-name
                :set item
                :where (concat
                         [:and]
                         (for [id-key id-keys]
                           [:= id-key (get item id-key)]))}))))

(defn create-get-fn [table-name id-keys]
  (let [id-keys (if (sequential? id-keys) id-keys [id-keys])]
    (fn [item fields]
      (cond-> (db/get {:select (if (sequential? fields) fields [fields])
                       :from [table-name]
                       :where (concat
                                [:and]
                                (for [id-key id-keys]
                                  [:= id-key (get item id-key)]))})
        (keyword? fields) fields))))

(def insert-vote! (create-insert-fn :votes votes-column-names {:insert-or-replace? true}))


(defn reset-votes! [{:keys [:vote/voter :vote/survey :vote/survey-id]}]
  (db/run! {:delete-from :votes
            :where [:and
                    [:= :vote/voter voter]
                    [:= :vote/survey survey]
                    [:= :vote/survey-id survey-id]]}))


#_ (defn reset-vote! [{:keys [:vote/voter :vote/survey :vote/option :vote/stake]}]
  (db/run! {:delete-from :votes
            :where [:and
                    [:= :vote/voter voter]
                    [:= :vote/survey survey]
                    [:= :vote/option option]
                    [:= :vote/stake stake]]}))

(defn all-unique-voters-per-survey
  "Returns a set of all the unique voters for a given survey."
  [survey-address survey-id]
  (set
   (db/all
    {:select [:vote/voter] :from [:votes]
     :where [:and
             [:= :vote/survey survey-address]
             [:= :vote/survey-id survey-id]]})))

(defn all-unique-voters
  "Returns a set of all the unique voters across a collection of surveys."
  [survey-address survey-ids]
  (set
   (db/all
    {:select [:vote/voter] :from [:votes]
     :where [:and
             [:= :vote/survey survey-address]
             [:in :vote/survey-id survey-ids]]})))

(defn total-staked-per-voter
  "Returns the amount a voter has staked across a collection of surveys."
  [survey-address survey-ids voter]
  (reduce
   (fn [stake survey-id]
     (+
      stake
      (:vote/stake (db/get
                    {:select [:vote/stake]
                     :from [:votes]
                     :where [:and
                             [:= :vote/survey survey-address]
                             [:= :vote/survey-id survey-id]
                             [:= :vote/voter (:vote/voter voter)]]}))))
   survey-ids))

(defn total-staked
  "Returns the ammount staked for all the voters."
  [survey-address survey-ids]
  (reduce
    +
    (map :vote/stake
         (db/all {:select [:vote/stake] :from [:votes]
                  :where [:and
                          [:= :vote/survey survey-address]
                          [:in :vote/survey-id survey-ids]]}))))

(defn percentage-staked
  "Returns the percentage staked for a particular voter."
  [survey-address survey-ids voter]
  (->
   (/
    (total-staked-per-voter survey-address survey-ids voter)
    (total-staked survey-address survey-ids))
   (* 100)))

(defn percentage-dank
  "For a given collection of survey-ids and the total dank allocated
  returns the dank for that particular voter based upon the percentage
  they staked."
  [survey-address survey-ids total-dank voter]
  (* total-dank
     (percentage-staked survey-address survey-ids voter)))

(defn compute-dank-allotment
  "For a given collection of survey-ids at a survey-address compute the
  dank allotment for each voter based upon the total dank in the pool
  and the percentage of DNT each voter staked across all of the voting
  rounds."
  [survey-address survey-ids total-dank]
  (let [voters (all-unique-voters survey-address survey-ids)]
    (map (juxt
        :vote/voter
        (partial percentage-dank survey-address survey-ids total-dank))
       voters)))

(comment
  ;; First group of surveys, we skipped survey 1
  ;; 8M Dank to be divided up amongst the voters
  (compute-dank-allotment "0x582ccc8fecacb8cbc3ad280f32194022a64f9ca3"
                          [0 2 3 4 5 6]
                          8000000))
