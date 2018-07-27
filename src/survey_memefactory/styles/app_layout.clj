(ns survey-memefactory.styles.app-layout
  (:require [garden.def :refer [defstyles]]
            [survey-memefactory.styles.base.media :refer [for-media-max]]
            [survey-memefactory.styles.base.grid :refer [grid-columns]]
            [clojure.string :as s]
            [garden.units :refer [px em]]))


(defstyles core
  [:.app-container
   {:display :grid
    :min-height (em 90)}
   (grid-columns "100%")])
