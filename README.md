# HistoryPornBot
 
Scrape images/title from https://old.reddit.com/r/HistoryPorn/new/ using Python with the PRAW (https://praw.readthedocs.io/) Reddit API Wrapper and then post to twitter using Twitter4j (http://twitter4j.org/) Java API Wrapper.

Currently running live on a Raspberry PI 4 using the Twitter account: HistoryPornBot (https://twitter.com/HistoryPornBot)

## Requirements
Twitter Developer

 - [https://developer.twitter.com/en](https://developer.twitter.com/en)
 
Reddit Developer
 - [https://www.reddit.com/dev/api/](https://www.reddit.com/dev/api/)

Java + Below libraries

 - [http://twitter4j.org/](http://twitter4j.org/)

Python + Below libraries

 - [https://praw.readthedocs.io/en/latest/](https://praw.readthedocs.io/en/latest/)

## Running

### API Keys
*Twitter API & Reddit API keys need to be added.*

**Twitter**: TwitterHandler,java class:

	private final static String CONSUMER_KEY = "KEY";
    private final static String CONSUMER_KEY_SECRET = "SECRET";
    private final static String ACCESS_TOKEN = "ACCESS";
    private final static String ACCESS_TOKEN_SECRET = "SECRET";
    
**Reddit**: get-reddit-posts.py Python file:

    reddit = praw.Reddit(client_id='ID', \
                         client_secret='SECRET', \
                         user_agent='APP NAME', \
                         username='REDDIT USERNAME', \
                         password='REDDIT PASSWORD')

## 3 Folder Locations Defined by User
The application will use 3 different folder locations:

 - **Posts** *(After a post is made it is placed in this location)*
 - **Images** *(Images to be posted are saved here from Reddit)*
 - **Scripts** *(Python files are found here)*

Find a location for these folders and change the path in the following:

**RedditHandler.java:**

    private final static String PYTHON_SCRIPTS_LOCATION = "/root/Documents/bot/scripts/";
    private final static String PYTHON_GET_POSTS = PYTHON_SCRIPTS_LOCATION + "get-reddit-posts.py";

**PostQueue.java:**

    private final static String SAVE_IMAGE_LOCATION = "/root/Documents/bot/images/";
    private final static String POST_ARCHIVE_LOCATION = "/root/Documents/bot/posts/";
**Optional Options:**

**Timing:** 

 - **LOOP_TIME_TYPE** (*In milliseconds so 1000 = 1 second, 60000 = 1 minute etc...*)
 - **LOOP_TIME_AMOUNT** (*The amount of the above time standard*)

HistoryPornBot.java:

    private final static int LOOP_TIME_TYPE = 1000; 
    private final static int LOOP_TIME_AMOUNT = 1;
    
**Subreddit:**

RedditHandler.java

    private final static String PYTHON_SUBREDDIT = "historyporn";

**Amount of Posts to Grab**

RedditHandler.java

    private final static String AMOUNT_OF_POSTS = "10";

## Compile

**Compile to JAR and Run JAR:**

Compiled to JAR and ran through terminal using: root@raspberrypi:~/Desktop# java -jar HistoryPornBot.jar
