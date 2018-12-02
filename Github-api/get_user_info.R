install.packages("jsonlite")
library(jsonlite)
install.packages("httpuv")
library(httpuv)
install.packages("httr")
library(httr)

 # Can be github, linkedin etc depending on application
oauth_endpoints("github")

 # Change based on what your application is
github_app = oauth_app(appname = "SW-APP",
                  key = "58f19bd30ba3dc77b571",
                  secret = "342fcd11765297dc79346bebc39992203d24778b")

 # Get OAuth credentials
github_token <- oauth2.0_token(oauth_endpoints("github"), github_app)



#halt here to cache, select Yes...



 # Use API
gtoken <- config(token = github_token)
req <- GET("https://api.github.com/users/bill-coman/repos", gtoken)

 # Take action on http error
stop_for_status(req)

 # Extract content from a request
json1 = content(req)

 # Convert to a data.frame
gitDF = jsonlite::fromJSON(jsonlite::toJSON(json1))

 # Subset data.frame
gitDF[gitDF$full_name == "bill-coman/LCA-Project", "created_at"] 

 # The code above was sourced from Michael Galarnyk's blog, found at:
# https://towardsdatascience.com/accessing-data-from-github-api-using-r-3633fb62cb08

#improve structure of data collection...

#Profile -> bill-coman
myProfile = fromJSON("https://api.github.com/users/bill-coman")
myProfile$followers
myProfile$public_repos


#FURTHER DETAILS RE MY FOLLOWERS

myFollowers = fromJSON("https://api.github.com/users/bill-coman/followers")
n = length(myFollowers$login)

for(i in 1:n){
 if (i == 1){
    print(paste("|   MY FOLLOWERS:  ( Total = ",n,")   |"))  
 }
 print(paste("|",i,"|   ",myFollowers$login[i], sep=""))
}

#FURTHER DETAILS RE MY REPOS
myRepos = fromJSON("https://api.github.com/users/bill-coman/repos")
n = length(myRepos$name)

for(i in 1:n){
 if (i == 1){
    print(paste("|   MY REPOS:  ( Total = ",n,")   |"))  
 }
 print(paste("|",i,"|   ",myRepos$name[i]," -> Created: ",myRepos$created_at[i],sep=""))
}
#COMPLETE

#Confirm Ability To Add a differnet user to the Data.frame
#NB variable RH holds the data
 # Use API
RH = GET("https://api.github.com/users/RoryDH/followers?per_page=100;", gtoken)
 # Take action on http error
stop_for_status(RH)
 # Extract content from a request
ex = content(RH)
 # Convert to a data.frame
gitDF = jsonlite::fromJSON(jsonlite::toJSON(ex))
 # Subset data.frame
gitDF $login
#COMPLETE COMPLETE
