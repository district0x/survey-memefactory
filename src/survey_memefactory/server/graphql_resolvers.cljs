(ns survey-memefactory.server.graphql-resolvers
  (:require
    [cljs-web3.core :as web3-core]
    [cljs-web3.eth :as web3-eth]
    [cljs.nodejs :as nodejs]
    [clojure.string :as str]
    [clojure.string :as string]
    [district.graphql-utils :as graphql-utils]
    [district.server.db :as db]
    [honeysql.core :as sql]
    [honeysql.helpers :as sqlh]
    [print.foo :refer [look] :include-macros true]
    [survey-memefactory.server.db :as meme-db]
    [survey-memefactory.shared.surveys :refer [surveys surveys-map]]
    [taoensso.timbre :as log]))


(defn surveys-resolver []
  (->> surveys
    (filter :survey/address)))


(defn survey-total-votes [{:keys [:survey/address]}]
  (second (first (db/get {:select [(sql/call :sum :vote/stake)]
                          :from [:votes]
                          :where [:= :vote/survey (str/lower-case address)]}))))


(defn survey-voter-votes [{:keys [:survey/address]} {:keys [:voter]}]
  (if (web3-core/address? voter)
    (second (first (db/get {:select [(sql/call :sum :vote/stake)]
                            :from [:votes]
                            :where [:and
                                    [:= :vote/survey (str/lower-case address)]
                                    [:= :vote/voter (str/lower-case voter)]]})))
    0))


(defn survey-options [{:keys [:survey/address]} {:keys [:voter]}]
  (if (web3-core/address? address)
    (->> (get-in surveys-map [address :survey/options])
      (map (fn [{:keys [:option/id] :as option}]
             (merge
               option
               {:option/survey address
                :option/total-votes
                (or (second (first (db/get {:select [(sql/call :sum :vote/stake)]
                                            :from [:votes]
                                            :where [:and
                                                    [:= :vote/survey (str/lower-case address)]
                                                    [:= :vote/option id]]})))
                    0)}))))
    []))


(defn option-voter-voted? [{:keys [:option/survey :option/id]} {:keys [:voter]}]
  (let [voter (str/lower-case voter)]
    (boolean (and (web3-core/address? voter)
                  (seq (db/get {:select [:vote/option]
                                :from [:votes]
                                :where [:and
                                        [:= :vote/survey (str/lower-case survey)]
                                        [:= :vote/option id]
                                        [:= :vote/voter voter]]}))))))


(def resolvers-map
  {:Query {:surveys surveys-resolver}
   :Survey {:survey/total-votes survey-total-votes
            :survey/voter-votes survey-voter-votes
            :survey/options survey-options}
   :Option {:option/voter-voted? option-voter-voted?}})
