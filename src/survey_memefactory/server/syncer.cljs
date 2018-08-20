(ns survey-memefactory.server.syncer
  (:require
    [bignumber.core :as bn]
    [camel-snake-kebab.core :as cs :include-macros true]
    [cljs-web3.core :as web3]
    [cljs-web3.eth :as web3-eth]
    [district.server.config :refer [config]]
    [district.server.smart-contracts :refer [instance replay-past-events]]
    [district.server.web3 :refer [web3]]
    [district.web3-utils :as web3-utils]
    [mount.core :as mount :refer [defstate]]
    [print.foo :refer [look] :include-macros true]
    [survey-memefactory.server.contract.survey :as survey]
    [survey-memefactory.server.db :as db]
    [survey-memefactory.shared.surveys :refer [surveys]]
    [taoensso.timbre :as log])
  (:require-macros [survey-memefactory.server.macros :refer [try-catch]]))

(declare start)
(declare stop)
(defstate ^{:on-reload :noop} syncer
  :start (start (merge (:syncer @config)
                       (:syncer (mount/args))))
  :stop (stop syncer))


(defn cast-vote-event [_ {:keys [:args :address] :as e}]
  (log/info "Cast Vote Event" {:args args} ::cast-vote-event)
  (try-catch
    (let [{:keys [:voter :option :stake :survey-id]} args]
      (db/reset-votes! {:vote/voter voter
                        :vote/survey address
                        :vote/survey-id (bn/number survey-id)})
      (db/insert-vote! {:vote/voter voter
                        :vote/option (bn/number option)
                        :vote/stake (bn/number stake)
                        :vote/survey address
                        :vote/survey-id (bn/number survey-id)}))))



(defn start [opts]
  (when-not (web3/connected? @web3)
    (throw (js/Error. "Can't connect to Ethereum node")))
  (let [surveys (->> surveys
                  (map :survey/address)
                  (remove nil?)
                  distinct)]
    (doall
      (concat
        (map (fn [address]
               (survey/cast-vote-event address
                                       {}
                                       "latest"
                                       cast-vote-event))
             surveys)
        (map (fn [address]
               (-> (survey/cast-vote-event
                     address
                     {}
                     {:from-block 0 :to-block "latest"})
                 (replay-past-events cast-vote-event)))
             surveys)))))

(defn stop [syncer]
  (doseq [filter (remove nil? @syncer)]
    (web3-eth/stop-watching! filter (fn [err]))))
