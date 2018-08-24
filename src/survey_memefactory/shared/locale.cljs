(ns survey-memefactory.shared.locale
  "Includes multi-method for internationalization of clojure data structures
  
   Example can be seen in survey-memefactory.ui.home.page

   Adding a new language localization:

   1. create a defmethod based on `get-locale*`
   2. add the language prefix to `supported-languages`
   3. don't forget to require the `defmethods`"
  (:require
   [taoensso.timbre :refer-macros [warn]]))


(def supported-languages
  #{:en})   ;; English (US)
  
;; Uncomment upon addition
;;    :ko   ;; Korean
;;    :cn}) ;; Chinese (Simplified)


(defmulti get-locale*
  (fn [language key args]
    (vec [(keyword language) (keyword key)])))


(defn get-locale
  [language key & args]
  (get-locale* language key args))


;; Default Dispatch
;; Raises an error for supported languages with unknown key, uses
;; default (:en) for unsupported languages.
(defmethod get-locale* :default
  [language key args]
  (if (contains? supported-languages language)
    (throw (js/Error. (str "Failed to find language template: " [language key])))
    (do
      (warn "Unknown locale used " (pr-str language) ", Using default :en")
      (get-locale* :en key args))))
  
