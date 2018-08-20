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
    [district.ui.web3-tx-id.subs :as tx-id-subs]
    [survey-memefactory.ui.components.app-layout :refer [app-layout]]
    [cljs-time.core :as t]
    [print.foo :include-macros true]
    [bignumber.core :as bn]))

(defn options [{:keys [:survey/options :survey/end-date :survey/address] :as args}]
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
                pending? "Voting..."
                voter-voted? "Voted"
                :else "Vote")])]
          [:div.votes-bar-body
           [:div.bar [:span.bar-index
                      {:style {:width percentage}}]]
           [:div.percentage
            (format/format-number (bn/number (web3/from-wei total-votes :ether))
                                  {:max-fraction-digits 2})
            " (" percentage ")"]]])))])


(defn survey [{:keys [:survey/title :survey/description :key :survey/total-votes :survey/voter-votes] :as args}]
  [:div.survey
   [:div.title key ". " title]
   [:div.description description]
   [:div.total-votes
    [:span.label "Start Date: "]
    (format/format-date (:survey/start-date args))]
   [:div.total-votes
    [:span.label "End Date: "]
    (format/format-date (:survey/end-date args))]
   [:div.total-votes
    [:span.label "Total Votes: "]
    (format/format-token (bn/number (web3/from-wei total-votes :ether))
                         {:token "DNT"})]
   [:div.total-votes
    [:span.label "You Voted: "]
    (format/format-token (bn/number (web3/from-wei voter-votes :ether))
                         {:token "DNT"})
    " (" (format/format-percentage voter-votes total-votes) ")"]
   [:div.survey-address
    [:span.label "Contract Address: "
     (if (:survey/address args)
       [:a {:href (str "https://etherscan.io/address/" (:survey/address args))
            :target :_blank}
        (:survey/address args)]
       "Not deployed yet")]]
   (when (:survey/address args)
     [:div.survey-address
      [:span.label "Survey ID: " (:survey/id args)]])
   (when (:survey/address args)
     [options args])])


(defn calculate-total [surveys key]
  (-> (reduce (fn [acc item]
                (+ acc (or (get item key) 0)))
              0
              surveys)
    (web3/from-wei :ether)))


(defn total-stats [{:keys [:surveys]}]
  (let [total-votes (calculate-total surveys :survey/total-votes)
        voter-votes (calculate-total surveys :survey/voter-votes)
        percentage (format/format-percentage voter-votes total-votes)]
    [:div.total-stats
     [:div "Total Votes: " (format/format-token (bn/number total-votes) {:token "DNT"})]
     [:div "You Voted: " (format/format-token (bn/number voter-votes) {:token "DNT"})]
     [:div "You are eligible to obtain "
      (format/format-token (* (/ voter-votes total-votes) 1000000000)
                           {:token "DANK"})
      " (" percentage ")"]]))


(defmethod page :route/home []
  (let [active-account (subscribe [::accounts-subs/active-account])]
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
                               {:refetch-on #{:vote-success :auto-refresh}}])]
        [app-layout
         {:meta {:title "survey-memefactory"
                 :description "Description"}}
         [:div.home
          [:h2.title "MemeFactory Survey"]
          [:p "Welcome to the Meme Factory Community Design Survey. Below are listed public polls which will decide the design and implementation of several aspects of the upcoming Meme Factory dApp, as well as the winners of the Community Design Contest entries."]
          [:p "Participation requires a MetaMask or mycrypto.com-compatible wallet with an available district0x Network Token (DNT) balance, as well as enough Ether to cover any transaction costs." [:b "The DNT balance available for each vote is fixed based on the balance available at the beginning of each survey. No DNT will be transferred away from your account during voting."]]
          [:p "Upon conclusion of all votes, every vote cast in each survey will be rewarded with a proportion of the newly minted DANK, an ERC20 token crucial to the curation of content on Meme Factory. These DANK rewards will be sent " [:b "back to the exact address holding the voting DNT"] ", so be sure to plan accordingly."]
          [:p "For more information, see our announcement " [:a {:href "https://blog.district0x.io/meme-factory-community-surveys-839033f03c14" :target :_blank} "blog post"] "." [:br]
           "For tutorials on voting, see the " [:a {:href "https://www.youtube.com/watch?v=9hBtPY6L_lw" :target :_blank} "MetaMask"] " or " [:a {:href "https://www.youtube.com/watch?v=8e6rDUxxLjg" :target :_blank} "MyCrypto Tutorials"]]
          [total-stats
           {:surveys (:surveys query)}]
          [:div.surveys
           (doall
             (for [[i s] (medley/indexed surveys)]
               [survey (merge s
                              {:key (inc i)}
                              (get-in query [:surveys i]))]))]]
         ]))))
