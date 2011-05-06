package shared;

import java.util.HashMap;

public class ForumThread implements java.io.Serializable{
	private String id = null;
	private String title = null;
    private String content = null;
    private String topicId = null;
    private String userName = null;
	private String created = null;
	
	public ForumThread(){};
	
	public ForumThread(String id, String title, String content, String topicId, String userName, String created){
		this.id = id;
		this.title = title;
		this.content = content;
        this.topicId = topicId;
        this.userName = userName;
	    this.created = userName;
	}
	
	public String id(){ return id; }
	public String title(){ return title; }
	public String content(){ return content; }
	public String topicId(){ return topicId; }
	
	public String userName() {return userName;}
	public void userName(String userName) {this.userName = userName;}

	public String created() {return created;}


	
	public String toString(){
	    HashMap<String, String> map = new HashMap<String, String>();
	    map.put("id", id);
	    map.put("title", title);
	    map.put("content", content);
	    map.put("userName", userName);
	    map.put("topicId", topicId);
	    map.put("created", created);
	    return map.toString();
	}
	

}
