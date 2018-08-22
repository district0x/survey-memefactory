(ns survey-memefactory.shared.locale.chinese
  (:require
   [survey-memefactory.shared.locale :refer [get-locale*]]))


(defmethod get-locale*
  [:cn :welcome-paragraph-1] [_ _ _]

  [:p "Welcome!"])
