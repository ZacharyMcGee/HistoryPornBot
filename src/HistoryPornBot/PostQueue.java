package HistoryPornBot;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PostQueue {
	// A queue of post objects
	public static Queue<Post> postQueue = new LinkedList<>();
	
	// Reddit handler - get posts from reddit and download images 
	public static RedditHandler rh = new RedditHandler();
	
	// Twitter handler using Twitter4j - create tweets 
	public static TwitterHandler th = new TwitterHandler();
	
	// Use this to calculate starting point for trimming from url
    private final static String REDDIT_URL_PREFIX = "https://";
    
    // The location to save reddit images to
    private final static String SAVE_IMAGE_LOCATION = "/root/Documents/bot/images/";
    
    // The location to save the images after they are posted to twitter
    private final static String POST_ARCHIVE_LOCATION = "/root/Documents/bot/posts/";
	
	public void LoadPostQueue() {
		// Get posts from Reddit using python script with praw library and add to a list 
        List<String> posts = rh.GetRedditPosts();
		
        // Loop through all the returned posts
        for(int i = 0; i < posts.size(); i++) {
        	
        	// Posts come back as 1 string of title and url and using a space as a delimiter 
        	String post = posts.get(i);
        	
        	// Url is first so split post into url by starting at beginning to the first space(url won't contain a space unlike title)
        	String url = post.substring(0, post.indexOf(' '));
        	
        	// Title is the remaining string after the first space, clean the title of any [] or () 
        	String title = CleanTitle(post.substring(url.length() + 1, post.length()));
       	
        	// Sometimes a post might not be an image, make sure the url contains a jpg or png image format
        	if(url.contains(".jpg") || url.contains(".png")) {
        		
        		// Save file name as the image name, must remove url to get
        		String name = StripFileNameFromURL(url);
        		
        		// Where to save the image
        		String imagePath = BuildSaveImagePath(name);
        		
        		// Where to save after posting to twitter
        		String postPath = BuildPostPath(name);
        		
        		// Make sure the image doesn't exist in imagePath or postPath (We don't want to double post)
        		if(!new File(imagePath).exists() && !new File(postPath).exists()) {
        			
        			// If we successfully download then
        			if(rh.DownloadImage(url, imagePath)) {
        				
        				// Create a new post object with the details
        				Post p = new Post(name, title, imagePath);
        				
        				// And add to the post queue for posting
        				postQueue.add(p);
        			}
        		}
        	}
        }
	}
	
	public void PostFromQueue() {
		File file = new File(postQueue.peek().GetPostImage());
		try {
			System.out.println("POSTING: " + postQueue.peek().GetPostTitle());
			//th.Tweet(postQueue.peek().GetPostTitle(), file);
		}
		catch (Exception ex) {
			// TODO: Handle exceptions (log?)
		}
		file.renameTo(new File(POST_ARCHIVE_LOCATION + postQueue.peek().GetPostName()));
		postQueue.remove();
	}
	
	public void CleanPendingPosts() {
		String[] pendingImages;
		
		File file = new File(SAVE_IMAGE_LOCATION);
		
		pendingImages = file.list();
		
		for(String pendingImage : pendingImages) {
			File pendingImageFile = new File(SAVE_IMAGE_LOCATION + pendingImage);
			pendingImageFile.delete();
		}
	}
	
	public boolean IsPostQueueEmpty() {
		return postQueue.isEmpty();
	}
	
	private static String CleanTitle(String s) {
		s = RemoveBracketsFromText(s);
		s = RemoveParenthesisFromText(s);
		
		return s;
	}
    
	private static String RemoveBracketsFromText(String s) {
		return s.replaceAll("\\[.*?\\]","");
	}
	
	private static String RemoveParenthesisFromText(String s) {
		return s.replaceAll("\\(.*?\\)","");
	}
	
	private static String BuildSaveImagePath(String name) {
		return (SAVE_IMAGE_LOCATION + name);
	}
	
	private static String BuildPostPath(String name) {
		return (POST_ARCHIVE_LOCATION + name);
	}
	
	private static String StripFileNameFromURL(String url) {
		// Trim HTTPS:// from the url 
		url = url.substring(REDDIT_URL_PREFIX.length(), url.length());
		
		// return the substring of the url starting from "/" character indicating file name
		return url.substring(url.indexOf("/") + 1, url.length());
	}
}
