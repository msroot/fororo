package shared;

public class Comment implements java.io.Serializable{
	private String id = null;
	private String content = null;
	private String userName = null;
	private String threadId = null;
	private String created = null;
	
	public Comment(){};
	
	public Comment(String id, String content, String userName, String threadId, String created){
	    this.id = id;
	    this.content = content;
	    this.userName = userName;
	    this.threadId = threadId;
	    this.created = created;
	}
		
	public String id() { return id; }
	public String content(){ return content; }
	public String userName(){ return userName; }
	public String threadId(){ return threadId; }
	public String created(){ return created; }
	 
}