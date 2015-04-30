##curl -H "Content-Type: application/json" -d '{ "features": [2, 0, 0] }' http://localhost:8000/queries.json

library(data.table)
library(httr)
test <- read.csv("~/workspace/kaggling/otto/test.csv")

#exclude <- names(test) %in% c("id")
#no_id <- test[!exclude]

url <- "http://localhost:8000/queries.json"

extract_features <- function(row){
  emp <- "del"
  for(i in 2:length(row)){
    emp <- paste(emp, row[i], sep= ",")
  }
  proc <- gsub("del,", "", emp)
  
  paste('{ "features": [ ', proc, '] } ', sep = "")
}
  
  predict_row <- function(features){
    POST(url,
        body = features,
        encode = 'json',
        add_headers("Content-Type" = "application/json")
    )
    
  }

test <- head(test)

df <- data.frame()

for(i in 1:nrow(test)){
  lab <- content(predict_row(extract_features(no_id[i,])))$label
  d <- data.frame(id = test[i,1], target = )
}
