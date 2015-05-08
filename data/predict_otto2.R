##curl -H "Content-Type: application/json" -d '{ "features": [2, 0, 0] }' http://localhost:8000/queries.json
# { "features": [ 0,0,0,0,0,0,0,0,0,3,0,0,0,3,2,1,0,0,0,0,0,0,0,5,3,1,1,0,0,0,0,0,1,0,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,11,1,20,0,0,0,0,0] }
# curl -H "Content-Type: application/json" -d ' {"features": [ 0,0,0,0,0,0,0,0,0,3,0,0,0,3,2,1,0,0,0,0,0,0,0,5,3,1,1,0,0,0,0,0,1,0,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,11,1,20,0,0,0,0,0] }'  http://localhost:8000/queries.json
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

#test <- head(test)

df <- data.frame()

for(i in 1:nrow(test)){
  pred <- predict_row(extract_features(test[i,]))
  lab <- content(pred)$label
  d <- data.frame(id = test[i,1], target = lab)
  df <- rbind(df, d)
  
  if(i %% 1000 == 0){
    cat(i, "... \n")
  }
}

write.csv(df, "/Users/nl/workspace/kaggling/otto/pio-1.csv", row.names = FALSE)
