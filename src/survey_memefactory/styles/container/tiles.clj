(ns survey-memefactory.styles.container.tiles
  (:require
   [garden.def :refer [defstyles]]
   [garden.stylesheet :refer [at-media]]
   [clojure.string :as s]
   [survey-memefactory.styles.base.colors :refer [color]]
   [survey-memefactory.styles.base.media :refer [for-media-min for-media-max]]
   [garden.units :refer [px]]))

(defstyles core
  [:.tiles
   {:display :flex
    :flex-wrap :wrap
    :justify-content :space-evenly}])
