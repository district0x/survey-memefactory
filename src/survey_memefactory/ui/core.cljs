(ns survey-memefactory.ui.core
  (:require
    [cljs.spec.alpha :as s]
    [district.ui.component.router :refer [router]]
    [district.ui.graphql]
    [district.ui.notification]
    [district.ui.now]
    [district.ui.reagent-render]
    [district.ui.router-google-analytics]
    [district.ui.router]
    [district.ui.smart-contracts]
    [district.ui.web3-account-balances]
    [district.ui.web3-accounts]
    [district.ui.web3-balances]
    [district.ui.web3-sync-now]
    [district.ui.web3-tx-id]
    [district.ui.web3]
    [survey-memefactory.ui.home.events]
    [district.ui.web3-tx]
    [survey-memefactory.shared.graphql-schema :refer [graphql-schema]]
    [survey-memefactory.shared.routes :refer [routes]]
    [survey-memefactory.shared.smart-contracts :refer [smart-contracts]]
    [survey-memefactory.ui.home.page]
    [mount.core :as mount]
    [print.foo :include-macros true]
    [re-frame.core :refer [dispatch]]))

(def debug? ^boolean js/goog.DEBUG)

(defn dev-setup []
  (when debug?
    (enable-console-print!)))


(defn ^:export init []
  (s/check-asserts debug?)
  (dev-setup)
  (-> (mount/with-args
        (merge {:web3 {:url "http://localhost:8549"}
                :smart-contracts {:contracts smart-contracts}
                :reagent-render {:id "app"
                                 :component-var #'router}
                :router {:routes routes
                         :default-route :route/home}
                :router-google-analytics {:enabled? (not debug?)}
                :graphql {:schema graphql-schema
                          :url (if debug?
                                 "http://localhost:6901/graphql"
                                 "https://api.survey.memefactory.io")}}))
      (mount/start))
  (dispatch [:init]))
