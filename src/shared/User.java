package shared;

public class User implements java.io.Serializable{
	public enum Type {ANONYMOUS, NORMAL, ADMIN};
	private Type type = Type.ANONYMOUS;
	private String name = null;
	private String password = null;
	
	public User(){};
	
	public User(String name, String password, Type type){
		this.name = name;
		this.password = password;
		this.type = type;
	}
	
	public Type type() { return type; }
	public String name(){ return name; }
	public String password(){ return password; }
}
