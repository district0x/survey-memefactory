(ns survey-memefactory.shared.locale.korean
  (:require
   [survey-memefactory.shared.locale :refer [get-locale*]]))


(defmethod get-locale*
  [:ko :welcome-paragraph-1] [_ _ _]

  [:p "Welcome!"])

