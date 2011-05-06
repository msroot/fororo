package server.db;

import java.util.List;

import shared.*;

 
public class getConfigTest {
	public static void main(String[] args) {
				 
	
		Config c = new Config("Welcome to fororo forum! hope u have a great time and meet a lot of putas tested! works?");
	Config d =DBConfig.update(c);
	System.out.println(d.message());
	}
}