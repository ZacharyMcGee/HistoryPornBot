package HistoryPornBot;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RedditHandler {
    private final static String AMOUNT_OF_POSTS = "10";
    private final static String PYTHON_EXECUTION_COMMAND = "python3 ";
    private final static String PYTHON_SCRIPTS_LOCATION = "/root/Documents/bot/scripts/";
    private final static String PYTHON_GET_POSTS = PYTHON_SCRIPTS_LOCATION + "get-reddit-posts.py";
    private final static String PYTHON_SUBREDDIT = "historyporn";
 
	public List<String> GetRedditPosts() {
		List<String> posts = new ArrayList<String>();
		String s = null;
		
		try {
	        Process p = Runtime.getRuntime().exec(BuildPythonCommand());
	        
	        BufferedReader stdInput = new BufferedReader(new 
	             InputStreamReader(p.getInputStream()));
	
	        BufferedReader stdError = new BufferedReader(new 
	             InputStreamReader(p.getErrorStream()));
	
	        while ((s = stdInput.readLine()) != null) {
	        	posts.add(s);
	        }

	        while ((s = stdError.readLine()) != null) {
	            //rtnString = null;
	        }
	    }
	    catch (IOException e) {
	        e.printStackTrace();
	        System.exit(-1);
	    }
		
		return posts;
	}
	
	public boolean DownloadImage(String path, String destination) {
		try {
			URL url = new URL(path);
			InputStream in = new BufferedInputStream(url.openStream());
			OutputStream out = new BufferedOutputStream(new FileOutputStream(destination));
	
			for ( int i; (i = in.read()) != -1; ) {
			    out.write(i);
			}
			in.close();
			out.close();
		}
		catch (Exception ex) {
			return false;
		}
		return true;
	}
	
	public static String BuildPythonCommand() {
		return (PYTHON_EXECUTION_COMMAND + PYTHON_GET_POSTS + " " + AMOUNT_OF_POSTS + " " + PYTHON_SUBREDDIT);
	}
	
}
