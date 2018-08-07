(ns survey-memefactory.server.contract.survey
  (:require
    [bignumber.core :as bn]
    [camel-snake-kebab.core :as cs :include-macros true]
    [cljs-solidity-sha3.core :refer [solidity-sha3]]
    [cljs-web3.eth :as web3-eth]
    [district.server.smart-contracts :refer [contract-call instance contract-address]]))

(defn get-survey [contract-addr survey-id]
  (contract-call [:survey contract-addr] survey-id))

(defn cast-vote-event [contract-addr & args]
  (apply contract-call [:survey contract-addr] :CastVote args))

(defn reset-vote-event [contract-addr & args]
  (apply contract-call [:survey contract-addr] :ResetVote args))

(defn start-survey-event [contract-addr & args]
  (apply contract-call [:survey contract-addr] :StartSurvey args))



