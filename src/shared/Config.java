package shared;

import java.util.*;

public class Config implements java.io.Serializable{
	private String message = null;
	public Config(){};
		
	

 
		
	public Config(String message){
	    this.message = message;
	    }
	
	public String message() { return message; }
	public void message(String message) { this.message = message;}
	}