curl -H "Content-Type: application/json" -d '{ "features": [2, 0, 0] }' http://localhost:8000/queries.json

library(httr)
test <- fread("~/workspace/kaggling/otto/test.csv")

url <- "http://localhost:8000/queries.json"

extract_features <- function(row)

predict_row <- function(features){
  GET(url,
      body = features,
      add_headers("Content-Type" = "application/json")
  )

}
