package HistoryPornBot;

public class HistoryPornBot { 
	// This is in milliseconds. A millisecond is .001 seconds therefore setting to 1000 would be 1 second
    private final static int LOOP_TIME_TYPE = 1000; 
    
    // How many of the above value do we want? If we set to seconds and this to 10, then it will run every 10 seconds
    private final static int LOOP_TIME_AMOUNT = 1;
    
	public static void main(String[] args) {
		System.out.println("Starting");
		
		// Create a PostQueue object for handling the post queue
		PostQueue pq = new PostQueue();
		
		// Delete images from images folder on run
		pq.CleanPendingPosts();
		
		// Main loop
	    try {
	        while (true) {
	        	// Check if we have any posts queued
	        	if(pq.IsPostQueueEmpty()) {	
	        		// It is empty, attempt to load.
	        		pq.LoadPostQueue();
	        	}
	        	else
	        	{
	        		// We have posts ready, make a tweet
	        		pq.PostFromQueue();
	        	}
	        	// Delay the next run(don't want to make a post every 5 seconds)
	            Thread.sleep(LOOP_TIME_AMOUNT * LOOP_TIME_TYPE);
	        }
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
    }
}
