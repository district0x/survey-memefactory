(ns survey-memefactory.styles.core
  (:require [garden.def :refer [defstyles]]
            [garden.units :refer [px]]
            [survey-memefactory.styles.app-layout :as app-layout]
            [survey-memefactory.styles.component.app-bar :as app-bar]
            [survey-memefactory.styles.component.main-content :as main-content]
            [survey-memefactory.styles.component.compact-tile :as compact-tile]
            [survey-memefactory.styles.component.form :as form]
            [survey-memefactory.styles.container.tiles :as tiles]
            [survey-memefactory.styles.app-menu :as app-menu]
            [survey-memefactory.styles.pages.home :as page.home]))

(defstyles main
  app-layout/core
  app-menu/core
  main-content/core
  app-bar/core
  tiles/core
  compact-tile/core
  page.home/core
  form/core)
