(ns survey-memefactory.shared.surveys
  (:require [cljs-time.core :as t]))

(def surveys
  [{:survey/address "0x5679f3908ea3eece5c33f8c7e183cc81e87963a0"
    :survey/id 2
    :survey/title "Meme Format"
    :survey/description "The purpose of this vote is to pick a “format” standard for each Meme. Given the technical and visual design constraints we’re working with, we have a few possible choices for aspect ratio for Memes themselves, based on popular resolutions:"
    :survey/start-date (t/date-time 2018 8 13)
    :survey/end-date (t/plus (t/date-time 2018 8 13) (t/weeks 1))
    :survey/options [{:option/id 1 :option/text "5:7 (Standard physical playing card size)"}
                     {:option/id 2 :option/text "2:3 (Vertical phone screen)"}
                     {:option/id 3 :option/text "4:3 (Standard TV/motion picture)"}
                     {:option/id 4 :option/text "16:9 (Standard HD/Widescreen)"}]}
   {:survey/address nil
    :survey/title "Website Theme"
    :survey/description "In the early stages of planning the website design, we had several different color pallete swaps drawn up, each of which is meant to fit the entire theme of Meme Factory:"
    :survey/start-date (t/date-time 2018 8 20)
    :survey/end-date (t/plus (t/date-time 2018 8 20) (t/weeks 1))
    :survey/options [{:option/id 1 :option/text "Option A"}
                     {:option/id 2 :option/text "Option B"}
                     {:option/id 3 :option/text "Option C"}]}])


(def surveys-map
  (zipmap (remove nil? (map :survey/address surveys)) surveys))