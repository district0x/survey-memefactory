(ns survey-memefactory.styles.component.main-content
  (:require [garden.def :refer [defstyles]]
            [garden.stylesheet :refer [at-media]]
            [clojure.string :as s]
            [survey-memefactory.styles.base.icons :refer [icons]]
            [survey-memefactory.styles.base.borders :refer [border-top]]
            [survey-memefactory.styles.base.colors :refer [color]]
            [garden.selectors :as sel]
            [garden.units :refer [pt px em rem]]))


(defstyles core
  [:.main-content
   {:min-height (em 90)
    :box-shadow "inset 20px 20px 30px rgba(0,0,0,0.05)"
    :display :flex
    :justify-content :center
    :background (color :main-content-bg)}])
