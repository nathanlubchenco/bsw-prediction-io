library(RCurl)
library(jsonlite)
library(httr)

train <- read.csv("~/workspace/kaggling/otto/train.csv")

url <- "http://localhost:7070/events.json?accessKey=V4aJ8mxUHFaCcLa9cqB451ygIRB1I5JAkokMIwoQByiTgovtqWId589mgVf2GGWJ"

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
  
  #paste('{ "features": [ ', proc, '] } ', sep = "")
  proc
}

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
post_attr <- function(feature_name, feature, id){
  
  POST(url, 
       body = create_payload(feature_name, feature, id),
       encode = 'json',
       add_headers("Content-Type" = "application/json")
       )
}

post_record <- function(row){
  POST(url, 
       body = create_full_payload(row),
       encode = 'json',
       add_headers("Content-Type" = "application/json")
  )
}

#train <- head(train)
#train <- sample_n(train, 100)
#exclude <- names(train) %in% c("id")
#train <- train[!exclude]

cnames <- colnames(train)

#for(i in 1:nrow(train)){
#  for(j in 2:length(cnames)){
#    post_attr(cnames[j], train[i,j], paste(train[i,1]))
#  }
#  if(i %% 10 == 0){
#    cat(i, "... \n")
#  }
#}

for(i in 1:nrow(train)){
  post_record(train[i,])
  
  if(i %% 1000 == 0){
    cat(i, "... \n")
  }
}

