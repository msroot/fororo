package shared;

public class Comment implements java.io.Serializable{
	private String id = null;
	private String content = null;
	private String userId = null;
	private String threadId = null;
	
	
	public Comment(){};
	
	public Comment(String id, String content, String userId, String threadId){
	    this.id = id;
	    this.content = content;
	    this.userId = userId;
	    this.threadId = threadId;
	}
		
	public String id() { return id; }
	public String content(){ return content; }
	public String userId(){ return userId; }
	public String threadId(){ return threadId; }
	 
}