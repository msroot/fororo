package server.db;

import java.util.List;

import shared.*;

public class getConfigTest {
	public static void main(String[] args) {

		Config c = new Config("Welcome to fororo forum! "
				+ "hope u have a great time!");
		Config d = DBConfig.update(c);
		System.out.println(d.message());

		System.out.println("--------------");
		Config v = DBConfig.get();
		System.out.println(v.message());

	}
}