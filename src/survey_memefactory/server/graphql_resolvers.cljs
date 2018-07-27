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
    [survey-memefactory.shared.surveys :refer [surveys]]
    [taoensso.timbre :as log]))


(defn surveys-resolver []
  surveys)


(defn survey-total-votes [{:keys [:survey/address]}]
  (if (web3-core/address? address)
    (second (first (db/get {:select [(sql/call :sum :vote/stake)]
                            :from [:votes]
                            :where [:= :vote/survey (str/lower-case address)]})))
    0))


(defn survey-voter-votes [{:keys [:survey/address]} {:keys [:voter]}]
  (if (and (web3-core/address? voter)
           (web3-core/address? address))
    (second (first (db/get {:select [(sql/call :sum :vote/stake)]
                            :from [:votes]
                            :where [:and
                                    [:= :vote/survey (str/lower-case (print.foo/look address))]
                                    [:= :vote/voter (str/lower-case (print.foo/look voter))]]})))
    0))


(def resolvers-map
  {:Query {:surveys surveys-resolver}
   :Survey {:survey/total-votes survey-total-votes
            :survey/voter-votes survey-voter-votes}})
