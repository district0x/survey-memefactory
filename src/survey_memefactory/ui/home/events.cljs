(ns survey-memefactory.ui.home.events
  (:require
    [district.ui.smart-contracts.queries :as contract-queries]
    [district.ui.web3-accounts.queries :as account-queries]
    [district.ui.web3-tx.events :as tx-events]
    [re-frame.core :as re-frame :refer [reg-event-fx]]
    [district0x.re-frame.interval-fx]))


(def interceptors [re-frame/trim-v])

(reg-event-fx
  :init
  [interceptors]
  (fn [{:keys [:db]}]
    {:dispatch-interval {:dispatch [:auto-refresh]
                         :id :auto-refresh
                         :ms 10000}}))

(reg-event-fx
  :auto-refresh
  (constantly nil))

(reg-event-fx
  :vote
  [interceptors]
  (fn [{:keys [:db]} [{:keys [:option/id :survey/address]}]]
    (let [active-account (account-queries/active-account db)]
      {:dispatch [::tx-events/send-tx {:instance (contract-queries/instance db :survey address)
                                       :fn :vote-option
                                       :args [0 id]
                                       :tx-opts {:from active-account
                                                 :gas 200000}
                                       :tx-id {:survey/address address :option/id id}
                                       :on-tx-hash [:vote-tx-hash]
                                       :on-tx-success [:vote-success]
                                       :on-tx-error [:vote-error]}]})))


(reg-event-fx
  :vote-tx-hash
  [interceptors]
  (fn [{:keys [:db]} []]
    (println "vote tx hash")
    nil))


(reg-event-fx
  :vote-success
  [interceptors]
  (fn [{:keys [:db]} []]
    (println "vote success")
    nil))


(reg-event-fx
  :vote-error
  [interceptors]
  (fn [{:keys [:db]} []]
    (println "vote error")
    nil))
