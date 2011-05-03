package shared;

public class ThreadTopic implements java.io.Serializable{
	private String id = null;
	private String name = null;
	private String description = null;
	
	public ThreadTopic(){};
	
	public ThreadTopic(String id, String name, String description){
	    this.id = id;
	    this.name = name;
	    this.description = description;
	};
	
	public String id() { return id; }
	public String name(){ return name; }
	public String description(){ return description; }
}