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
   [(sql/call :primary-key :vote/voter :vote/survey :vote/option)]])

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

