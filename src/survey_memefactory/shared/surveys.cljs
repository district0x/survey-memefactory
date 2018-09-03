(ns survey-memefactory.shared.surveys
  (:require [cljs-time.core :as t]))

(def surveys
  [{:survey/address "0x582ccc8fecacb8cbc3ad280f32194022a64f9ca3"
    :survey/id 0
    :survey/title "Meme Format"
    :survey/description "The purpose of this vote is to pick a “format” standard for each Meme. Given the technical and visual design constraints we’re working with, we have a few possible choices for aspect ratio for Memes themselves, based on popular resolutions:"
    :survey/start-date (t/date-time 2018 8 13)
    :survey/end-date (t/plus (t/date-time 2018 8 13) (t/weeks 1))
    :survey/options [{:option/id 1 :option/text "5:7 (Standard physical playing card size)"}
                     {:option/id 2 :option/text "2:3 (Vertical phone screen)"}
                     {:option/id 3 :option/text "4:3 (Standard TV/motion picture)"}
                     {:option/id 4 :option/text "16:9 (Standard HD/Widescreen)"}]}
   {:survey/address "0x582ccc8fecacb8cbc3ad280f32194022a64f9ca3"
    :survey/id 2
    :survey/title "Website Theme"
    :survey/description "In the early stages of planning the website design, we had several different color pallete swaps drawn up, each of which is meant to fit the entire theme of Meme Factory:"
    :survey/start-date (t/date-time 2018 8 20)
    :survey/end-date (t/plus (t/date-time 2018 8 20) (t/weeks 1))
    :survey/options [{:option/id 1 :option/image "theme1.jpg"}
                     {:option/id 2 :option/image "theme2.jpg"}
                     {:option/id 3 :option/image "theme3.jpg"}
                     {:option/id 4 :option/image "theme4.jpg"}
                     {:option/id 5 :option/image "theme5.jpg"}
                     {:option/id 6 :option/image "theme6.jpg"}]}
   {:survey/address "0x582ccc8fecacb8cbc3ad280f32194022a64f9ca3"
    :survey/id 3
    :survey/title "Homepage Lists"
    :survey/description "We’ve implemented several different sorting functions that will give us ways to make gallery style shortlists of 6 memes (similar to Hot/New/Trending on Reddit) and put them on the homepage. Several of the top voted lists below will be featured on the homepage, ranked in order of their total vote-count."
    :survey/start-date (t/date-time 2018 8 27)
    :survey/end-date (t/plus (t/date-time 2018 8 27) (t/weeks 1))
    :survey/options [{:option/id 1 :option/text "New on Marketplace - Memes most recently listed for sale"}
                     {:option/id 2 :option/text "Rare Finds - Memes for sale with the least number minted in total"}
                     {:option/id 3 :option/text "Random Picks - A random selection of Memes currently for sale"}
                     {:option/id 4 :option/text "Fresh Submissions - The most recent submissions to the Dank Registry"}
                     {:option/id 5 :option/text "Active Challenges -  Dank Registry challenges ending soonest"}
                     {:option/id 6 :option/text "Trending Votes - Challenges with the largest total vote participation in the past 24hrs"}]}
   {:survey/address "0x582ccc8fecacb8cbc3ad280f32194022a64f9ca3"
    :survey/id 4
    :survey/title "Logo of Token"
    :survey/description "The DANK token is a newly issued, standard ERC20 token used to power the deposit, vote, and challenge functions of the Dank Registry. Like any token, DANK needs a logo to be easily recognizable in wallets and clients. We accepted community designs for this logo, and included a few of our own below as well:"
    :survey/start-date (t/date-time 2018 9 3)
    :survey/end-date (t/plus (t/date-time 2018 9 3) (t/weeks 1))
    :survey/options [{:option/id 1 :option/image "dank1.png"}
                     {:option/id 2 :option/image "dank2.png"}
                     {:option/id 3 :option/image "dank3.svg"}
                     {:option/id 4 :option/image "dank4.png"}
                     {:option/id 5 :option/image "dank5.png"}
                     {:option/id 6 :option/image "dank6.png"}
                     {:option/id 7 :option/image "dank7.svg"}
                     {:option/id 8 :option/image "dank8.png"}
                     {:option/id 9 :option/image "dank9.png"}
                     {:option/id 10 :option/image "dank10.svg"}
                     {:option/id 11 :option/image "dank11.svg"}
                     {:option/id 12 :option/image "dank12.svg"}
                     {:option/id 13 :option/image "dank13.svg"}
                     {:option/id 14 :option/image "dank14.svg"}
                     {:option/id 15 :option/image "dank15.svg"}
                     {:option/id 16 :option/image "dank16.svg"}
                     {:option/id 17 :option/image "dank17.svg"}
                     {:option/id 18 :option/image "dank18.svg"}
                     {:option/id 19 :option/image "dank19.svg"}
                     {:option/id 20 :option/image "dank20.svg"}
                     {:option/id 21 :option/image "dank21.svg"}
                     {:option/id 22 :option/image "dank22.svg"}
                     {:option/id 23 :option/image "dank23.svg"}
                     {:option/id 24 :option/image "dank24.svg"}
                     {:option/id 25 :option/image "dank25.svg"}
                     {:option/id 26 :option/image "dank26.svg"}
                     {:option/id 27 :option/image "dank27.svg"}
                     {:option/id 28 :option/image "dank28.svg"}
                     {:option/id 29 :option/image "dank29.svg"}
                     {:option/id 30 :option/image "dank30.svg"}
                     {:option/id 31 :option/image "dank31.svg"}
                     {:option/id 32 :option/image "dank32.png"}
                     {:option/id 33 :option/image "dank33.png"}
                     {:option/id 34 :option/image "dank34.png"}
                     {:option/id 35 :option/image "dank35.png"}
                     {:option/id 36 :option/image "dank36.svg"}
                     {:option/id 37 :option/image "dank37.svg"}]}
   {:survey/address nil
    :survey/title "Back-of-meme Design"
    :survey/description "Each meme on Meme Factory will have its own completely unique artwork covering the “front” of the collectable. When users on the dApp click the meme it will flip over, revealing a back-of-the-card design which will be shared across all collectibles on the site. The back will also feature an overlay of all the metadata related to that meme. Below we’ve listed some community-made designs as well as our own:"
    :survey/start-date (t/date-time 2018 9 10)
    :survey/end-date (t/plus (t/date-time 2018 9 10) (t/weeks 1))
    :survey/options [{:option/id 1 :option/text "Option A"}
                     {:option/id 2 :option/text "Option B"}
                     {:option/id 3 :option/text "Option C"}]}])


(def surveys-map
  (zipmap (remove (comp nil? first) (map #(vec [(:survey/address %) (:survey/id %)]) surveys)) surveys))