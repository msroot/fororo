package shared;

public class ForumThread implements java.io.Serializable{
	private String id = null;
	private String title = null;
    private String description = null;
	
	public ForumThread(){};
	
	public ForumThread(String id, String title, String description){
		this.name = name;
		this.title = title;
		this.description = description;
	}
	
	public String id(){ return id; }
	public String title(){ return title; }
	public String description(){ return description; }
}
