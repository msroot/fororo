package shared;

public class ForumThread implements java.io.Serializable{
	private String id = null;
	private String title = null;
    private String content = null;
    private String topicId = null;
	
	public ForumThread(){};
	
	public ForumThread(String id, String title, String description, String topicId){
		this.id = id;
		this.title = title;
		this.content = content;
        this.topicId = topicId;
	}
	
	public String id(){ return id; }
	public String title(){ return title; }
	public String content(){ return content; }
	public String topicId(){ return topicId; }
}
