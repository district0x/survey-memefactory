(ns survey-memefactory.styles.pages.home
  (:require [garden.def :refer [defstyles]]
            [garden.stylesheet :refer [at-media]]
            [clojure.string :as s]
            [survey-memefactory.styles.base.icons :refer [icons]]
            [survey-memefactory.styles.base.borders :refer [border-top]]
            [survey-memefactory.styles.base.colors :refer [color]]
            [survey-memefactory.styles.base.fonts :refer [font]]
            [survey-memefactory.styles.base.media :refer [for-media-min for-media-max]]
            [garden.selectors :as sel]
            [garden.units :refer [pt px em rem]]
            [clojure.string :as str]))


(defstyles core
  [:.home
   {:background (color :meme-panel-bg)
    :margin-top (em 3)
    :margin-bottom (em 2)
    :box-shadow ".3em .3em 0px 0px rgba(0,0,0,0.05)"
    :display :block
    :position :relative
    :padding (em 2)
    :border-radius "1em 1em 1em 1em"
    :width "50%"
    :font-size (px 15)
    :line-height (px 22)}
   (for-media-max :wide
                  [:&
                   {:width "55%"}])
   (for-media-max :large
                  [:&
                   {:width "75%"}])
   (for-media-max :computer
                  [:&
                   {:width "85%"}])
   (for-media-max :tablet
                  [:&
                   {:width "90%"}])
   (for-media-max :mobile
                  [:&
                   {:width "95%"}])

   [:h2.title
    (font :bungee)
    (for-media-max :tablet
                   [:&
                    {:font-size (px 18)}])
    {:white-space :nowrap
     :position :relative
     :color (color :pink)
     :font-size (px 25)
     :margin-top (em 0.3)
     :margin-bottom (em 1)
     :text-align :center}]
   [:.total-stats
    {:margin-top (em 2)}]
   [:.survey
    {:margin-top (em 2)}
    [:.title {:font-weight :bold}]]]

  )
