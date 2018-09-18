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
                     {:option/id 16 :option/image "dank16.png"}
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
   {:survey/address "0x582ccc8fecacb8cbc3ad280f32194022a64f9ca3"
    :survey/id 5
    :survey/title "Back-of-meme Design"
    :survey/description "Each meme on Meme Factory will have its own completely unique artwork covering the “front” of the collectable. When users on the dApp click the meme it will flip over, revealing a back-of-the-card design which will be shared across all collectibles on the site. The back will also feature an overlay of all the metadata related to that meme. Below we’ve displayed some community made designs, as well as our own. \n\nNOTE: Some submissions include the meme stats and button overlays. We will be incorporating these overlays into the winning design ourselves. Pick your favorite card design, not button!"
    :survey/start-date (t/date-time 2018 9 10)
    :survey/end-date (t/plus (t/date-time 2018 9 10 23) (t/weeks 1))
    :survey/options [{:option/id 1 :option/image "back1.png"}
                     {:option/id 2 :option/image "back2.jpeg"}
                     {:option/id 3 :option/image "back3.svg"}
                     {:option/id 4 :option/image "back4.svg"}
                     {:option/id 5 :option/image "back5.svg"}
                     {:option/id 6 :option/image "back6.png"}
                     {:option/id 7 :option/image "back7.svg"}
                     {:option/id 8 :option/image "back8.png"}
                     {:option/id 9 :option/image "back9.svg"}
                     {:option/id 10 :option/image "back10.png"}
                     {:option/id 11 :option/image "back11.png"}
                     {:option/id 12 :option/image "back12.svg"}]}
   {:survey/address "0x582ccc8fecacb8cbc3ad280f32194022a64f9ca3"
    :survey/id 6
    :survey/title "First Memes"
    :survey/description "The Meme Factory dApp serves the entire marketplace process for rare collectibles - from creation of the content to its inclusion to its final sale. In order to present a fully working product on launch day, it’s necessary for the system to already have memes available for sale and submissions available for challenge. \n\nHowever, the first few collectibles issued are likely to carry significant value for the novelty of being “first”. So we hosted an open competition to allow our community to submit Memes ahead of time. The winning submissions will be given an opportunity to mint their respective memes in order of ranking. These memes will be available for sale on the day of Meme Factory’s launch.\n"
    :survey/start-date (t/date-time 2018 9 17)
    :survey/end-date (t/plus (t/date-time 2018 9 17 23) (t/weeks 1))
    :survey/options [{:option/id 1 :option/image "meme1.jpg"}
                     {:option/id 2 :option/image "meme2.png"}
                     {:option/id 3 :option/image "meme3.gif"}
                     {:option/id 4 :option/image "meme4.png"}
                     {:option/id 5 :option/image "meme5.jpg"}
                     {:option/id 6 :option/image "meme6.jpg"}
                     {:option/id 7 :option/image "meme7.jpg"}
                     {:option/id 8 :option/image "meme8.jpg"}
                     {:option/id 9 :option/image "meme9.jpg"}
                     {:option/id 10 :option/image "meme10.jpg"}
                     {:option/id 11 :option/image "meme11.jpg"}
                     {:option/id 12 :option/image "meme12.jpg"}
                     {:option/id 13 :option/image "meme13.jpg"}
                     {:option/id 14 :option/image "meme14.gif"}
                     {:option/id 15 :option/image "meme15.jpg"}
                     {:option/id 16 :option/image "meme16.jpg"}
                     {:option/id 17 :option/image "meme17.gif"}
                     {:option/id 18 :option/image "meme18.png"}
                     {:option/id 19 :option/image "meme19.jpg"}
                     {:option/id 20 :option/image "meme20.jpg"}
                     {:option/id 21 :option/image "meme21.jpeg"}
                     {:option/id 22 :option/image "meme22.jpg"}
                     {:option/id 23 :option/image "meme23.jpg"}
                     {:option/id 24 :option/image "meme24.gif"}
                     {:option/id 25 :option/image "meme25.png"}
                     {:option/id 26 :option/image "meme26.png"}
                     {:option/id 27 :option/image "meme27.jpg"}
                     {:option/id 28 :option/image "meme28.jpg"}
                     {:option/id 29 :option/image "meme29.png"}
                     {:option/id 30 :option/image "meme30.jpg"}
                     {:option/id 31 :option/image "meme31.jpeg"}
                     {:option/id 32 :option/image "meme32.jpg"}
                     {:option/id 33 :option/image "meme33.png"}
                     {:option/id 34 :option/image "meme34.gif"}
                     {:option/id 35 :option/image "meme35.png"}
                     {:option/id 36 :option/image "meme36.jpg"}
                     {:option/id 37 :option/image "meme37.gif"}
                     {:option/id 38 :option/image "meme38.gif"}
                     {:option/id 39 :option/image "meme39.jpg"}
                     {:option/id 40 :option/image "meme40.svg"}
                     {:option/id 41 :option/image "meme41.jpg"}
                     {:option/id 42 :option/image "meme42.png"}
                     {:option/id 43 :option/image "meme43.jpg"}
                     {:option/id 44 :option/image "meme44.jpg"}
                     {:option/id 45 :option/image "meme45.png"}
                     {:option/id 46 :option/image "meme46.jpg"}
                     {:option/id 47 :option/image "meme47.png"}
                     {:option/id 48 :option/image "meme48.png"}
                     {:option/id 49 :option/image "meme49.jpg"}
                     {:option/id 50 :option/image "meme50.jpg"}
                     {:option/id 51 :option/image "meme51.gif"}
                     {:option/id 52 :option/image "meme52.jpg"}
                     {:option/id 53 :option/image "meme53.jpeg"}
                     {:option/id 54 :option/image "meme54.gif"}
                     {:option/id 55 :option/image "meme55.jpg"}
                     {:option/id 56 :option/image "meme56.gif"}
                     {:option/id 57 :option/image "meme57.jpg"}
                     {:option/id 58 :option/image "meme58.jpg"}
                     {:option/id 59 :option/image "meme59.gif"}
                     {:option/id 60 :option/image "meme60.png"}
                     {:option/id 61 :option/image "meme61.jpg"}
                     {:option/id 62 :option/image "meme62.jpeg"}
                     {:option/id 63 :option/image "meme63.gif"}
                     {:option/id 64 :option/image "meme64.jpg"}
                     {:option/id 65 :option/image "meme65.png"}
                     {:option/id 66 :option/image "meme66.gif"}
                     {:option/id 67 :option/image "meme67.jpg"}
                     {:option/id 68 :option/image "meme68.gif"}
                     {:option/id 69 :option/image "meme69.jpg"}
                     {:option/id 70 :option/image "meme70.jpg"}
                     {:option/id 71 :option/image "meme71.png"}
                     {:option/id 72 :option/image "meme72.png"}
                     {:option/id 73 :option/image "meme73.jpg"}
                     {:option/id 74 :option/image "meme74.png"}
                     {:option/id 75 :option/image "meme75.jpg"}
                     {:option/id 76 :option/image "meme76.jpg"}
                     {:option/id 77 :option/image "meme77.jpeg"}
                     {:option/id 78 :option/image "meme78.jpg"}
                     {:option/id 79 :option/image "meme79.jpg"}
                     {:option/id 80 :option/image "meme80.png"}
                     {:option/id 81 :option/image "meme81.gif"}
                     {:option/id 82 :option/image "meme82.png"}
                     {:option/id 83 :option/image "meme83.gif"}
                     {:option/id 84 :option/image "meme84.jpg"}
                     {:option/id 85 :option/image "meme85.gif"}
                     {:option/id 86 :option/image "meme86.gif"}
                     {:option/id 87 :option/image "meme87.jpg"}
                     {:option/id 88 :option/image "meme88.png"}
                     {:option/id 89 :option/image "meme89.JPG"}
                     {:option/id 90 :option/image "meme90.png"}
                     {:option/id 91 :option/image "meme91.png"}
                     {:option/id 92 :option/image "meme92.jpg"}
                     {:option/id 93 :option/image "meme93.jpg"}
                     {:option/id 94 :option/image "meme94.jpg"}
                     {:option/id 95 :option/image "meme95.jpg"}
                     {:option/id 96 :option/image "meme96.jpg"}
                     {:option/id 97 :option/image "meme97.jpg"}
                     {:option/id 98 :option/image "meme98.jpg"}
                     {:option/id 99 :option/image "meme99.jpg"}
                     {:option/id 100 :option/image "meme100.png"}
                     {:option/id 101 :option/image "meme101.gif"}
                     {:option/id 102 :option/image "meme102.png"}
                     {:option/id 103 :option/image "meme103.gif"}
                     {:option/id 104 :option/image "meme104.jpg"}
                     {:option/id 105 :option/image "meme105.jpg"}
                     {:option/id 106 :option/image "meme106.jpg"}
                     {:option/id 107 :option/image "meme107.png"}
                     {:option/id 108 :option/image "meme108.gif"}
                     {:option/id 109 :option/image "meme109.gif"}
                     {:option/id 110 :option/image "meme110.png"}
                     {:option/id 111 :option/image "meme111.png"}
                     {:option/id 112 :option/image "meme112.png"}
                     {:option/id 113 :option/image "meme113.png"}
                     {:option/id 114 :option/image "meme114.jpg"}
                     {:option/id 115 :option/image "meme115.png"}
                     {:option/id 116 :option/image "meme116.png"}
                     {:option/id 117 :option/image "meme117.png"}
                     {:option/id 118 :option/image "meme118.png"}
                     {:option/id 119 :option/image "meme119.png"}
                     {:option/id 120 :option/image "meme120.png"}
                     {:option/id 121 :option/image "meme121.png"}
                     {:option/id 122 :option/image "meme122.png"}
                     {:option/id 123 :option/image "meme123.png"}
                     {:option/id 124 :option/image "meme124.png"}
                     {:option/id 125 :option/image "meme125.png"}]}])


(def surveys-map
  (zipmap (remove (comp nil? first) (map #(vec [(:survey/address %) (:survey/id %)]) surveys)) surveys))