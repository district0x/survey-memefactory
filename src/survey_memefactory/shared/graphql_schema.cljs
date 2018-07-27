(ns survey-memefactory.shared.graphql-schema)

(def graphql-schema "
  scalar Date
  scalar Keyword

  type Query {
    surveys: [Survey]
  }

  type Survey {
    survey_address: String
    survey_voterVotes(voter: String!): Float
    survey_totalVotes: Float
  }
")
