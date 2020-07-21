package HistoryPornBot;

import java.io.File;

import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterHandler {
	
	private final static String CONSUMER_KEY = "KEY";
    private final static String CONSUMER_KEY_SECRET = "SECRET";
    private final static String ACCESS_TOKEN = "ACCESS";
    private final static String ACCESS_TOKEN_SECRET = "SECRET";
    
    public void Tweet(String message, File file) throws TwitterException {
    	try {
	    	Twitter twitter = getTwitterinstance();
	
	    	StatusUpdate status = new StatusUpdate(message);
	    	status.setMedia(file);
	    	twitter.updateStatus(status);
    	}
    	catch(Exception ex) {
    		
    	}
    }
    
    public static Twitter getTwitterinstance() {
    	ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setDebugEnabled(true)
    	  .setOAuthConsumerKey(CONSUMER_KEY)
    	  .setOAuthConsumerSecret(CONSUMER_KEY_SECRET)
    	  .setOAuthAccessToken(ACCESS_TOKEN)
    	  .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
    	TwitterFactory tf = new TwitterFactory(cb.build());
    	Twitter twitter = tf.getInstance();
    	
    	return twitter;
    }
    
}
