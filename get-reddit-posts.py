import requests
import praw
import sys

reddit = praw.Reddit(client_id='CLIENT ID', \
                     client_secret='CLIENT SECRET', \
                     user_agent='APP NAME', \
                     username='REDDIT USERNAME', \
                     password='REDDIT PASSWORD')

subreddit = reddit.subreddit(str(sys.argv[2]))

for submission in subreddit.new(limit=int(sys.argv[1])):
    print(submission.url + " " + submission.title)