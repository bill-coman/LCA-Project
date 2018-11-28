install.packages("jsonlite")
library(jsonlite)
install.packages("httpuv")
library(httpuv)
install.packages("httr")
library(httr)

install.packages("plotly")

require(devtools)
library(plotly)

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


#improve structure of displaying the data collected...

#display json output

#Profile -> bill-coman
myProfile = fromJSON("https://api.github.com/users/bill-coman")
myDataJSon = toJSON(myProfile, pretty = TRUE)
myDataJSon


#display data collected in the dataframe

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
gitDF$login
#COMPLETE COMPLETE


#Now I'm going to collect multiple users and add data to a new data frame
#collect users in a vector
ids = gitDF$login
usersFound = c(ids)

#vector to collect unique users
users = c()

#dataframe to store info of unique users
userDF = data.frame(
  
  user = integer(),
  follows = integer(),
  followedBy = integer(),
  repos = integer(),
  dateCreated = integer()
  
)

#successfully collects a list of users who follow RoryDH
for(i in 1:length(usersFound))
{
  #Retrieve a list of individual users 
  followsURL = paste("https://api.github.com/users/", usersFound[i], "/following", sep = "")
  followsRequest = GET(followsURL, gtoken)
  followsContent = content(followsRequest)
  
  
  #sets a df for current user for the next loop
  followsDF = jsonlite::fromJSON(jsonlite::toJSON(followsContent))
  followsLogin = followsDF$login

  #next step is to let each of these users act as the source user
    for (j in 1:length(followsLogin))
    {
      #Check user hasnt already been visited
      if (is.element(followsLogin[j], users) == FALSE)
      {
        
        users[length(users) + 1] = followsLogin[j]
        
        #api process
        followsUrl2 = paste("https://api.github.com/users/", followingLogin[j], sep = "")
        follows2 = GET(followsUrl2, gtoken)
        followsContent2 = content(follows2)
        followsDF2 = jsonlite::fromJSON(jsonlite::toJSON(followsContent2))
        
        #Retrieve each users inputs for userDF
        followsNumber = followsDF2$following
        followedByNumber = followsDF2$followers
        reposNumber = followsDF2$public_repos
        yearCreated = substr(followsDF2$created_at, start = 1, stop = 4)
        
        #Add users data to a new row in the data.frame
        userDF[nrow(userDF) + 1, ] = c(followsLogin[j], followsNumber, followedByNumber, reposNumber, yearCreated)
        
      }
      next
    }
    if(length(users) > 400)
    {
      break
    }
  next
}
userDF
#COMPLETE


#Link R to plotly. This creates online interactive graphs based on the d3js library
Sys.setenv("plotly_username"="bcoman")
Sys.setenv("plotly_api_key"="gCjS0hya48xJXGRfyO9c")

#best place to start is followers vs repos  - see if it works
aPlot = plot_ly(data = userDF, x = ~repos, y = ~followedBy, 
                  text = ~paste("Followers: ", followedBy, "<br>Repositories: ", 
                                repos, "<br>Date Created:", dateCreated), color = ~dateCreated)
aPlot 

api_create(plot1, filename = "Followers vs Repositories by Date")
#can be found at https://plot.ly/~bcoman/1/#/






