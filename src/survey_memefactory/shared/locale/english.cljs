(ns survey-memefactory.shared.locale.english
  (:require
   [survey-memefactory.shared.locale :refer [get-locale*]]))


(defmethod get-locale*
  [:en :page-title-header] [_ _ _]
  "MemeFactory Survey")


(defmethod get-locale*
  [:en :welcome-paragraph-1] [_ _ _]

  [:p "Welcome to the Meme Factory Community Design Survey. Below are
       listed public polls which will decide the design and
       implementation of several aspects of the upcoming Meme Factory
       dApp, as well as the winners of the Community Design Contest
       entries."])


(defmethod get-locale*
  [:en :welcome-paragraph-2] [_ _ _]

  [:p "Participation requires a MetaMask or mycrypto.com-compatible
       wallet with an available district0x Network Token (DNT)
       balance, as well as enough Ether to cover any transaction
       costs."
   [:b "The DNT balance available for each vote is fixed based on the
        balance available at the beginning of each survey. No DNT will
        be transferred away from your account during voting."]])


(defmethod get-locale*
  [:en :welcome-paragraph-3] [_ _ _]

  [:p "Upon conclusion of all votes, every vote cast in each survey
       will be rewarded with a proportion of the newly minted DANK, an
       ERC20 token crucial to the curation of content on Meme
       Factory. These DANK rewards will be sent "
   [:b "back to the exact address holding the voting DNT"] ", so be
        sure to plan accordingly."])


(defmethod get-locale*
  [:en :welcome-paragraph-4] [_ _ _]
  [:p "For more information, see our announcement "
   [:a {:href "https://blog.district0x.io/meme-factory-community-surveys-839033f03c14" :target :_blank}
    "blog post"]
   "." [:br]
   "For tutorials on voting, see the "
   [:a {:href "https://www.youtube.com/watch?v=9hBtPY6L_lw" :target :_blank} "MetaMask"]
   " or "
   [:a {:href "https://www.youtube.com/watch?v=ZNUHNnDg2f8" :target :_blank} "MyCrypto Tutorials"]])


(defmethod get-locale*
  [:en :welcome-paragraph-5] [_ _ _]
  [:p "You can find survey contract ABI "
   [:a {:href "./contracts/build/Survey.abi" :target :_blank} "here."]])


(defmethod get-locale*
  [:en :total-votes] [_ _ _]
  "Total Votes: ")


(defmethod get-locale*
  [:en :you-voted] [_ _ _]
  "You Voted: ")


(defmethod get-locale*
  [:en :you-are-eligible] [_ _ _]
  "You are eligible to obtain ")


(defmethod get-locale*
  [:en :label-start-date] [_ _ _]
  "Start Date: ")


(defmethod get-locale*
  [:en :label-end-date] [_ _ _]
  "End Date: ")


(defmethod get-locale*
  [:en :label-total-votes] [_ _ _]
  "Total Votes: ")


(defmethod get-locale*
  [:en :label-you-voted] [_ _ _]
  "You Voted: ")


(defmethod get-locale*
  [:en :label-contract-address] [_ _ _]
  "Contract Address: ")


(defmethod get-locale*
  [:en :not-deployed-yet] [_ _ _]
  "Not deployed yet")


(defmethod get-locale*
  [:en :label-survey-id] [_ _ _]
  "Survey ID: ")


(defmethod get-locale*
  [:en :voting...] [_ _ _]
  "Voting...")


(defmethod get-locale*
  [:en :voted] [_ _ _]
  "Voted")


(defmethod get-locale*
  [:en :vote] [_ _ _]
  "Vote")
