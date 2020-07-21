package HistoryPornBot;

public class Post {
	private String name = "";
	private String title = "";
	private String image = "";
	
	public String GetPostName() {
		return name;
	}
	
	public String GetPostTitle() {
		return title; 
	}
	
	public String GetPostImage() {
		return image;
	}
	
	public void SetPostName(String s) {
		name = s;
	}
	
	public void SetPostTitle(String s) {
		title = s;
	}
	
	public void SetPostImage(String s) {
		image = s;
	}
	
	public Post(String name, String title, String image) {
		SetPostName(name);
		SetPostTitle(title);
		SetPostImage(image);
	}
}
