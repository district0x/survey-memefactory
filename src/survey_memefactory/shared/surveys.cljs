(ns survey-memefactory.shared.surveys
  (:require [cljs-time.core :as t]))

(def surveys
  [{:survey/address "0x5679f3908ea3eece5c33f8c7e183cc81e87963a0"
    :survey/title "Preferred format for a meme image?"
    :survey/start-date (t/date-time 2018 6 6)
    :survey/end-date (t/date-time 2018 10 6)
    :survey/options [{:option/id 1 :option/text "200x100"}
                     {:option/id 2 :option/text "150x350"}
                     {:option/id 3 :option/text "190x345"}]}
   {:survey/address nil
    :survey/title "What size of a meme picture should be used?"
    :survey/start-date (t/date-time 2018 7 8)
    :survey/end-date (t/date-time 2018 10 6)
    :survey/options [{:option/id 1 :option/text "Option A"}
                     {:option/id 2 :option/text "Option B"}
                     {:option/id 3 :option/text "Option C"}]}])


(def surveys-map
  (zipmap (remove nil? (map :survey/address surveys)) surveys))