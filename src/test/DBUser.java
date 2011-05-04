package test;

import shared.*;
import java.util.*;

public class DBUser {
    public static ArrayList<User> users = null;

    public static void init(){
        if(users == null){
            users = new ArrayList<User>();
            users.add(new User("testuser", "abcd1234", User.Type.NORMAL, true));
            users.add(new User("edd", "abcd1234", User.Type.ADMIN, true));
            users.add(new User("john", "abcd1234", User.Type.ADMIN, false));
            users.add(new User("vic", "abcd1234", User.Type.NORMAL, true));
            users.add(new User("adam", "abcd1234", User.Type.NORMAL, false));
        }
    }

    public static User getByName(String name){
        init();
        for( User user : users ){
            if (user.name().equals(name)) { return user; }
        }
        return null;
    }

    public static List<User> getAll(){
        init();
        return users;
    }

    public static User create(User user){
        init();
        System.out.println(getByName(user.name()) == null);
        if (getByName(user.name()) != null){
            return null;
        }
        users.add(user);
        return user;
    }
    
    public static User update(User user){
        init();
        User existinUser = getByName(user.name());
        if (existinUser == null){
            return null;
        }
        users.remove(existinUser);
        users.add(user);
        return user;
    }

    public static void main(String[] args) {
      
    	System.out.println("getByName()");
        System.out.println(DBUser.getByName("vic").name());

        if (DBUser.getByName("vicoo") == null)
            System.out.println("null");

        System.out.println("getAll()");
        System.out.println(DBUser.getAll());

        System.out.println("create()");
        DBUser.create( new User("neo", "abcd1234", User.Type.NORMAL, true) );
        System.out.println(DBUser.getByName("neo").name());
        
        System.out.println("update()");
        System.out.println("before isActive: " +DBUser.getByName("edd").isActive());
        DBUser.update( new User("edd", "abcd1234", User.Type.ADMIN, false) );
        System.out.println("after isActive: " +DBUser.getByName("edd").isActive());
    }
}