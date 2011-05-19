package shared;

import java.util.HashMap;

/**
 * Main <{@link User} Class. Server/Client common class for RMI
 * 
 * @author Victor Nava
 * 
 */
public class User implements java.io.Serializable{
    public enum Type {NORMAL, ADMIN};
    private Type type = Type.NORMAL;
    private String name = null;
    private String password = null;
    private boolean isActive = true;
    private String created = null;

    public User(){};

    /**
     * User contractor
     * @param name
     * @param password
     * @param type
     * @param isActive
     * @param created
     */
    public User(String name, String password, Type type, boolean isActive, String created){
        this.name = name;
        this.password = password;
        this.type = type;
        this.isActive = isActive;
        this.created = created;
    }

    public Type type() { return type; }
    public String name(){ return name; }
    public String password(){ return password; }
    public boolean isActive(){ return isActive; }
    public void isActive(boolean isActive){ this.isActive = isActive; }
    public String created() {return created;}

    public String toString(){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name", name);
        map.put("password", password);
        map.put("type", type.toString());
        map.put("isActive", new Boolean(isActive).toString());
        map.put("created", created);
        return map.toString();
    }

    
}