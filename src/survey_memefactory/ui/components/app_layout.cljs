(ns survey-memefactory.ui.components.app-layout
  (:require
   [reagent.core :as r]
   [district.ui.component.active-account :refer [active-account]]
   [district.ui.component.active-account-balance :refer [active-account-balance]]
   [re-frame.core :refer [subscribe dispatch]]))


(defn app-bar []
  (let [my-addresses (r/atom nil);;(subscribe [:district0x/my-addresses])
        ]
    (fn []
      [:div.app-bar
       [:div.logo]
       [:div.account-section
        [active-account]]])))


(defn app-layout []
  (fn [{:keys [:meta :search-atom]} & children]
    [:div.app-container
     [:div.app-content
      [app-bar]
      (into [:div.main-content]
            children)]]))
