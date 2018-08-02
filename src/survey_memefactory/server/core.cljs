(ns survey-memefactory.server.core
  (:require
    [cljs.nodejs :as nodejs]
    [district.server.config :refer [config]]
    [district.server.logging]
    [district.server.middleware.logging :refer [logging-middlewares]]
    [district.server.web3-watcher]
    [district.server.graphql]
    [survey-memefactory.server.syncer]
    [survey-memefactory.server.db]
    [survey-memefactory.shared.smart-contracts]
    [survey-memefactory.shared.graphql-schema :refer [graphql-schema]]
    [survey-memefactory.server.graphql-resolvers :refer [resolvers-map]]
    [mount.core :as mount]
    [district.graphql-utils :as graphql-utils]
    [district.server.graphql.utils :as utils]
    [taoensso.timbre :refer-macros [info warn error]]))

(nodejs/enable-util-print!)

(defn -main [& _]
  (-> (mount/with-args
        {:config {:default {:web3 {:port 8545}
                            :logging {:level "info" :console? true}}}
         :smart-contracts {:contracts-var #'survey-memefactory.shared.smart-contracts/smart-contracts}
         :graphql {:port 6901
                   :middlewares [logging-middlewares]
                   :schema (utils/build-schema graphql-schema
                                               resolvers-map
                                               {:kw->gql-name graphql-utils/kw->gql-name
                                                :gql-name->kw graphql-utils/gql-name->kw})
                   :field-resolver (utils/build-default-field-resolver graphql-utils/gql-name->kw)
                   :path "/graphql"
                   :graphiql true}
         :web3-watcher {:on-online (fn []
                                     (warn "Ethereum node went online again")
                                     (mount/stop #'survey-memefactory.server.db/survey-memefactory-db)
                                     (mount/start #'survey-memefactory.server.db/survey-memefactory-db
                                                  #'survey-memefactory.server.syncer/syncer))
                        :on-offline (fn []
                                      (warn "Ethereum node went offline")
                                      (mount/stop #'survey-memefactory.server.syncer/syncer))}})
    (mount/start))
  (warn "System started" {:config @config}))

(set! *main-cli-fn* -main)
