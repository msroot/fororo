package shared;

public class Topic implements java.io.Serializable{
	private String id = null;
	private String name = null;
	private String description = null;
	private boolean isActive = false;
	
	public Topic(){};
	
	public Topic(String id, String name, String description, boolean isActive){
	    this.id = id;
	    this.name = name;
	    this.description = description;
	    this.isActive = isActive;
	}
	
	public String id() { return id; }
	public String name(){ return name; }
	public String description(){ return description; }
	public boolean isActive(){ return isActive; }
	public void isActive(boolean isActive){ this.isActive = isActive; }
}