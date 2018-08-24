(ns survey-memefactory.ui.home.page
  (:require
   [bignumber.core :as bn]
   [cljs-time.core :as t]
   [cljs-web3.core :as web3]
   [district.format :as format]
   [district.ui.component.page :refer [page]]
   [district.ui.graphql.subs :as gql]
   [district.ui.router.subs :as router-subs]
   [district.ui.web3-accounts.subs :as accounts-subs]
   [district.ui.web3-tx-id.subs :as tx-id-subs]
   [medley.core :as medley]
   [print.foo :include-macros true]
   [re-frame.core :refer [subscribe dispatch]] 
   [reagent.core :as r]
   [survey-memefactory.shared.surveys :refer [surveys]]
   [survey-memefactory.shared.locale :refer [get-locale]]
   [survey-memefactory.shared.locale.english]
   ;; Uncomment upon addition
   ;; [survey-memefactory.shared.locale.korean]
   ;; [survey-memefactory.shared.locale.chinese]
   [survey-memefactory.ui.components.app-layout :refer [app-layout]]))


(defn wrap-locale [lang]
  (partial get-locale (keyword lang)))


(defn options [{:keys [:survey/options :survey/end-date :survey/address :locale] :as args}]
  [:div.options
   (doall
    (for [{:keys [:option/id :option/text :option/image :option/total-votes :option/voter-voted?]} options]
      (let [percentage (format/format-percentage total-votes (:survey/total-votes args))
            pending? @(subscribe [::tx-id-subs/tx-pending? {:survey/address address
                                                            :survey/id (:survey/id args)
                                                            :option/id id}])]
        [:div.option
         {:key (str address (:survey/id args) id)}
         [:div.option-row
          (if text
            [:div.option.text id ". " text]
            [:img.option.image {:src (str "./assets/images/" image)}])
          (if (t/after? end-date (t/now))
            [:button.vote
             {:on-click (fn []
                          (when address
                            (dispatch [:vote {:option/id id
                                              :survey/id (:survey/id args)
                                              :survey/address address}])))
              :class (when (or (not address)
                               pending?
                               voter-voted?
                               (not @(subscribe [::accounts-subs/active-account])))
                       "disabled")}
             (cond
               pending? (locale :voting...)
               voter-voted? (locale :voted)
               :else (locale :vote))])]
         [:div.votes-bar-body
          [:div.bar [:span.bar-index
                     {:style {:width percentage}}]]
          [:div.percentage
           (format/format-number (bn/number (web3/from-wei total-votes :ether))
                                 {:max-fraction-digits 2})
           " (" percentage ")"]]])))])


(defn survey [{:keys [:survey/title :survey/description :key :survey/total-votes :survey/voter-votes :locale] :as args}]
  [:div.survey
   [:div.title key ". " title]
   [:div.description description]
   [:div.total-votes
    [:span.label (locale :label-start-date)]
    (format/format-date (:survey/start-date args))]
   [:div.total-votes
    [:span.label (locale :label-end-date)]
    (format/format-date (:survey/end-date args))]
   [:div.total-votes
    [:span.label (locale :label-total-votes)]
    (format/format-token (bn/number (web3/from-wei total-votes :ether))
                         {:token "DNT"})]
   [:div.total-votes
    [:span.label (locale :label-you-voted)]
    (format/format-token (bn/number (web3/from-wei voter-votes :ether))
                         {:token "DNT"})
    " (" (format/format-percentage voter-votes total-votes) ")"]
   [:div.survey-address
    [:span.label (locale :label-contract-address)
     (if (:survey/address args)
       [:a {:href (str "https://etherscan.io/address/" (:survey/address args))
            :target :_blank}
        (:survey/address args)]
       (locale :not-deployed-yet))]]
   (when (:survey/address args)
     [:div.survey-address
      [:span.label (locale :label-survey-id) (:survey/id args)]])
   (when (:survey/address args)
     [options (merge args {:locale locale})])])


(defn calculate-total [surveys key]
  (-> (reduce (fn [acc item]
                (+ acc (or (get item key) 0)))
              0
              surveys)
    (web3/from-wei :ether)))


(defn total-stats [{:keys [:surveys :locale]}]
  (let [total-votes (calculate-total surveys :survey/total-votes)
        voter-votes (calculate-total surveys :survey/voter-votes)
        percentage (format/format-percentage voter-votes total-votes)]
    [:div.total-stats
     [:div (locale :total-votes) (format/format-token (bn/number total-votes) {:token "DNT"})]
     [:div (locale :you-voted) (format/format-token (bn/number voter-votes) {:token "DNT"})]
     [:div (locale :you-are-eligible)
      (format/format-token (* (/ voter-votes total-votes) 1000000000)
                           {:token "DANK"})
      " (" percentage ")"]]))


(defmethod page :route/home []
  (let [active-account (subscribe [::accounts-subs/active-account])
        active-page (subscribe [::router-subs/active-page])]
    (fn []
      (let [query @(subscribe [::gql/query
                               {:queries [[:surveys
                                           [:survey/address
                                            :survey/id
                                            :survey/total-votes
                                            [:survey/voter-votes {:voter @active-account}]
                                            [:survey/options [:option/id
                                                              :option/text
                                                              :option/image
                                                              :option/total-votes
                                                              [:option/voter-voted?
                                                               {:voter @active-account}]]]]]]}
                               {:refetch-on #{:vote-success :auto-refresh}}])
            {:keys [lang] :or {lang "en"}} (:query @active-page)
            locale (wrap-locale lang)]
        [app-layout
         {:meta {:title "survey-memefactory"
                 :description "Description"}}
         [:div.home
          [:h2.title (locale :page-title-header)]
          (locale :welcome-paragraph-1)
          (locale :welcome-paragraph-2)
          (locale :welcome-paragraph-3)
          (locale :welcome-paragraph-4)          
          [total-stats
           {:locale locale
            :surveys (:surveys query)}]
          [:div.surveys
           (doall
            (for [[i s] (medley/indexed surveys)]
              [survey (merge s
                             {:key (inc i) :locale locale}
                             (get-in query [:surveys i]))]))]]
         ]))))
