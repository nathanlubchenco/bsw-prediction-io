library(jsonlite)
library(httr)

train <- read.csv("~/workspace/kaggling/otto/train.csv")

# replace accessKey with access key for your app
url <- "http://localhost:7070/events.json?accessKey=V4aJ8mxUHFaCcLa9cqB451ygIRB1I5JAkokMIwoQByiTgovtqWId589mgVf2GGWJ"

# conversion to numerics for pio compatibility
train$target <- mapply(function(var){
  if(var == "Class_1"){
    return(1)
  } else if(var == "Class_2" ){
    return(2)
  } else if(var == "Class_3" ){
    return(3)
  } else if(var == "Class_4" ){
    return(4)
  } else if(var == "Class_5" ){
    return(5)
  } else if(var == "Class_6" ){
    return(6)
  } else if(var == "Class_7" ){
    return(7)
  } else if(var == "Class_8" ){
    return(8)
  } else if(var == "Class_9" ){
    return(9)
  } else {
    return(2)
  }}
, train$target)

# for single attribute
create_payload <- function(feature_name, feature, id){
  paste(
    '{
  "event" : "$set",
  "entityType" : "product",
  "entityId" : ', id, ',
  "properties" : {
    "',feature_name, '" : ',feature, '
  },
  "eventTime" : "2015-04-30T05:58:24.939Z"
}', sep = ""
    )
}

extract_features <- function(row){
  emp <- 'del'
  cnames <- colnames(row)
  for(i in 2:length(row)){
    f <- paste('"', cnames[i], '" : ', row[i], sep ="")
    emp <- paste(emp, f, sep= ",")
  }
  proc <- gsub("del,", "", emp)
  proc
}

# for all attributes (roughly 100x speedup)
create_full_payload <- function(row){
  paste(
    '{
  "event" : "$set",
  "entityType" : "product",
  "entityId" : ', row[1], ',
  "properties" : {
    ', extract_features(row), '
  },
  "eventTime" : "2015-04-30T05:58:24.939Z"
}', sep = ""
    )
}

# for single attribute
post_attr <- function(feature_name, feature, id){
  
  POST(url, 
       body = create_payload(feature_name, feature, id),
       encode = 'json',
       add_headers("Content-Type" = "application/json")
       )
}

# for all attributes
post_record <- function(row){
  POST(url, 
       body = create_full_payload(row),
       encode = 'json',
       add_headers("Content-Type" = "application/json")
  )
}

cnames <- colnames(train)

# loop through all records and post each record
# very slow, recommend investigating other methods of batch uploading
for(i in 1:nrow(train)){
  post_record(train[i,])
  
  if(i %% 1000 == 0){
    cat(i, "... \n")
  }
}

