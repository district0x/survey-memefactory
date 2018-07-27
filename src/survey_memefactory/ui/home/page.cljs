(ns survey-memefactory.ui.home.page
  (:require
    [cljs-web3.core :as web3]
    [district.format :as format]
    [district.ui.component.page :refer [page]]
    [district.ui.graphql.subs :as gql]
    [district.ui.web3-accounts.subs :as accounts-subs]
    [medley.core :as medley]
    [re-frame.core :refer [subscribe dispatch]]
    [reagent.core :as r]
    [survey-memefactory.shared.surveys :refer [surveys]]
    [survey-memefactory.ui.components.app-layout :refer [app-layout]]))

(defn survey [{:keys [:survey/title :key :survey/total-votes :survey/voter-votes] :as args}]
  [:div.survey
   [:div.title key ". " title]
   [:div.total-votes
    [:span.label "Start Date: "]
    (format/format-date (:survey/start-date args))]
   [:div.total-votes
    [:span.label "Total Votes: "]
    (format/format-token (web3/from-wei total-votes :ether)
                         {:token "DNT"})]
   [:div.total-votes
    [:span.label "You Voted: "]
    (format/format-token (web3/from-wei voter-votes :ether)
                         {:token "DNT"})
    " (" (format/format-percentage total-votes voter-votes) ")"]
   [:div.survey-address
    [:span.label "Contract Address: "
     (if (:survey/address args)
       [:a {:href (str "https://etherscan.io/address/" (:survey/address args))
            :target :_blank}
        (:survey/address args)]
       "Not deployed yet")]]
   (when (:survey/url args)
     [:a {:href (:survey/url args) :target :_blank}
      "Aragon URL"])])


(defn calculate-total [surveys key]
  (-> (reduce (fn [acc item]
                (+ acc (or (get item key) 0)))
              0
              (print.foo/look surveys))
    (web3/from-wei :ether)))

(defn total-stats [{:keys [:surveys]}]
  (let [total-votes (calculate-total surveys :survey/total-votes)
        voter-votes (calculate-total surveys :survey/voter-votes)
        percentage (format/format-percentage total-votes voter-votes)]
    [:div.total-stats
     [:div "Total Votes: " (format/format-token total-votes {:token "DNT"})]
     [:div "You Voted: " (format/format-token voter-votes {:token "DNT"})]
     [:div "You are eligible to obtain "
      (format/format-token (* (/ voter-votes total-votes) 1000000000)
                           {:token "DANK"})
      " (" percentage ")"]]))


(defmethod page :route/home []
  (let [active-account (subscribe [::accounts-subs/active-account])]
    (fn []
      (let [query (print.foo/look @(subscribe [::gql/query
                                               {:queries [[:surveys
                                                           [:survey/address
                                                            :survey/total-votes
                                                            [:survey/voter-votes {:voter (or @active-account "")}]]]]}]))]
        [app-layout
         {:meta {:title "survey-memefactory"
                 :description "Description"}}
         [:div.home
          [:h2.title "MemeFactory Survey"]
          [:div "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."]
          [total-stats
           {:surveys (:surveys query)}]
          [:div.surveys
           (doall
             (for [[i s] (medley/indexed surveys)]
               [survey (merge s
                              {:key (inc i)}
                              (get-in query [:surveys i]))]))]]
         ]))))
