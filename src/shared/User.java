package shared;

public class User implements java.io.Serializable{
	public enum Type {NORMAL, ADMIN};
	private Type type = Type.NORMAL;
	private String name = null;
	private String password = null;
	private boolean isActive = true;
	private String created = null;
	
	public User(){};
	
	public User(String name, String password, Type type, boolean isActive, String created){
		this.name = name;
		this.password = password;
		this.type = type;
		this.isActive = isActive;
	}
	
	public Type type() { return type; }
	public String name(){ return name; }
	public String password(){ return password; }
	public boolean isActive(){ return isActive; }
	public String created() {return created;}
}