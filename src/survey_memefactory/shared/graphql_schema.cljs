(ns survey-memefactory.shared.graphql-schema)

(def graphql-schema "
  scalar Date
  scalar Keyword

  type Query {
    surveys: [Survey]
  }

  type Survey {
    survey_address: String
    survey_id: Int
    survey_voterVotes(voter: String): Float
    survey_totalVotes: Float
    survey_options: [Option]
  }

  type Option {
    option_id: Int
    option_survey: String
    option_surveyId: Int
    option_text: String
    option_image: String
    option_totalVotes: Float
    option_voterVoted_(voter: String): Boolean
  }
")
