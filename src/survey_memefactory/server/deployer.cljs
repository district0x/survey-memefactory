(ns survey-memefactory.server.deployer
  (:require [cljs-web3.eth :as web3-eth]
            [district.cljs-utils :refer [rand-str]]
            [district.server.config :refer [config]]
            [district.server.smart-contracts :refer [contract-event-in-tx contract-address deploy-smart-contract! write-smart-contracts!]]
            [district.server.web3 :refer [web3]]
            [district.shared.error-handling :refer [try-catch-throw]]
            [mount.core :as mount :refer [defstate]]
            [taoensso.timbre :as log]
            [cljs-web3.core :as web3]))

(defn deploy-minime-token-factory! [default-opts]
  (deploy-smart-contract! :minime-token-factory (merge default-opts {:gas 2300000})))

(defn deploy-minime-token! [default-opts]
  (deploy-smart-contract! :dank-token (merge default-opts
                                             {:gas 2200000
                                              :arguments [(contract-address :minime-token-factory)
                                                          (web3/to-wei 1000000000 :ether)]})))

(defn deploy [deploy-opts]
  (deploy-minime-token-factory! deploy-opts)
  (deploy-minime-token! deploy-opts))

;; (-> @district.server.smart-contracts/smart-contracts :contracts deref keys)
