(ns survey-memefactory.server.dev
  (:require
    [camel-snake-kebab.core :as cs :include-macros true]
    [cljs-time.core :as t]
    [cljs-web3.core :as web3]
    [cljs.nodejs :as nodejs]
    [cljs.pprint :as pprint]
    [cljs-web3.eth :as web3-eth]
    [cljs-web3.evm :as web3-evm]
    [district.server.config :refer [config]]
    [district.server.db :refer [db]]
    [district.server.graphql :as graphql]
    [district.server.logging :refer [logging]]
    [district.server.middleware.logging :refer [logging-middlewares]]
    [district.server.smart-contracts]
    [district.server.web3 :refer [web3]]
    [district.server.web3-watcher]
    [goog.date.Date]
    [graphql-query.core :refer [graphql-query]]
    [survey-memefactory.server.db]
    [survey-memefactory.server.graphql-resolvers :refer [resolvers-map]]
    [survey-memefactory.server.syncer]
    [district.graphql-utils :as graphql-utils]
    [survey-memefactory.shared.graphql-schema :refer [graphql-schema]]
    [survey-memefactory.shared.smart-contracts]
    [mount.core :as mount]
    [district.server.graphql.utils :as utils]
    [print.foo :include-macros true]
    [clojure.pprint :refer [print-table]]))

(nodejs/enable-util-print!)

(defn on-jsload []
  (graphql/restart {:schema (utils/build-schema graphql-schema
                                                resolvers-map
                                                {:kw->gql-name graphql-utils/kw->gql-name
                                                 :gql-name->kw graphql-utils/gql-name->kw})
                    :field-resolver (utils/build-default-field-resolver graphql-utils/gql-name->kw)}))

(defn resync []
  (mount/stop #'survey-memefactory.server.db/survey-memefactory-db
              #'survey-memefactory.server.syncer/syncer)
  (-> (mount/start #'survey-memefactory.server.db/survey-memefactory-db
                   #'survey-memefactory.server.syncer/syncer)
    pprint/pprint))

(defn -main [& _]
  (-> (mount/with-args
        {:config {:default {:logging {:level "info"
                                      :console? true}
                            :graphql {:port 6901
                                      :middlewares [logging-middlewares]
                                      :schema (utils/build-schema graphql-schema
                                                                  resolvers-map
                                                                  {:kw->gql-name graphql-utils/kw->gql-name
                                                                   :gql-name->kw graphql-utils/gql-name->kw})
                                      :field-resolver (utils/build-default-field-resolver graphql-utils/gql-name->kw)
                                      :path "/graphql"
                                      :graphiql true}
                            :web3 {:port 8545}}}
         :smart-contracts {:contracts-var #'survey-memefactory.shared.smart-contracts/smart-contracts
                           :print-gas-usage? true
                           :auto-mining? true}})
    (mount/start)
    pprint/pprint))

(set! *main-cli-fn* -main)