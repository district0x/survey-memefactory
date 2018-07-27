(ns survey-memefactory.shared.surveys
  (:require [cljs-time.core :as t]))

(def surveys
  [{:survey/address "0x7a2B3958dc3F61308896bf7e854d9689a6339D89"
    :survey/title "What size of a meme picture should be used?"
    :survey/url "http://mainnet.aragon.aragonpm.com/#/0x4ce45e59df378011cd987c412ec98b1e50b6e96b/0x7a2B3958dc3F61308896bf7e854d9689a6339D89"
    :survey/start-date (t/date-time 2018 6 6)}
   {:survey/address nil
    :survey/title "What size of a meme picture should be used?"
    :survey/url nil
    :survey/start-date (t/date-time 2018 7 8)}])