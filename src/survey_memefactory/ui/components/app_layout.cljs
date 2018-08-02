(ns survey-memefactory.ui.components.app-layout
  (:require
    [reagent.core :as r]
    [district.ui.component.active-account :refer [active-account]]
    [district.ui.component.active-account-balance :refer [active-account-balance]]
    [district.ui.web3-accounts.subs :as accounts-subs]
    [re-frame.core :refer [subscribe dispatch]]))


(defn app-bar []
  (let [active-acc (subscribe [::accounts-subs/active-account])]
    (fn []
      [:div.app-bar
       [:div.logo]
       [:div.account-section
        (if @active-acc
          [active-account]
          [:div.no-account "No Account Connected"])]])))


(defn app-layout []
  (fn [{:keys [:meta :search-atom]} & children]
    [:div.app-container
     [:div.app-content
      [app-bar]
      (into [:div.main-content]
            children)]]))
