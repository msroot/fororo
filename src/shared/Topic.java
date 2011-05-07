package shared;

import java.util.*;

public class Topic implements java.io.Serializable{
	private String id = null;
	private String name = null;
	private String description = null;
	private boolean isActive = false;
	private String userName = null;
	private String created = null;
	
	public Topic(){};
		
	public Topic(String id, String name, String description, boolean isActive, String userName, String created){
	    this.id = id;
	    this.name = name;
	    this.description = description;
	    this.isActive = isActive;
	    this.userName = userName;
	    this.created = created;
	}
	
	public String id() { return id; }
	public void id(String id) { this.id = id;}
	
	public String name(){ return name; }
	public void name(String name){ this.name = name;}
	
	public String description(){ return description; }
	public void description(String description){ this.description = description; }
	
	public boolean isActive(){ return isActive; }
	public void isActive(boolean isActive){ this.isActive = isActive; }
	
	public String userName() {return userName;}
	public void userName(String userName) {this.userName = userName;}

	public String created() {return created;}
	
	public String toString(){
	    HashMap<String, String> map = new HashMap<String, String>();
	    map.put("id", id);
	    map.put("name", name);
	    map.put("description", description);
	    map.put("isActive", new Boolean(isActive).toString());
	    map.put("userName", userName);
	    map.put("created", created);
	    return map.toString();
	}
	
	public static void main(String[] args) {
	   Topic t =  new Topic("1", "the name", "the description", true, "malaka", "now");
       System.out.println(t);
	}
}