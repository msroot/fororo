package server.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DBPreferences {

	public String server = "";
	public String port = "";
	public String database = "";
	public String user = "";
	public String pass = "";
	public File f = null;

	/**
	 * Read a file from disk and and adds values to user,pass etc file must
	 * have a single line and example: server:port:database:user:pass
	 */
	public DBPreferences() {
		this.f = new File("passwords");
		exec();
	}

	/**
	 * Same as above overloading for reading difrent file
	 * 
	 * @param path
	 */
	public DBPreferences(String path) {
		this.f = new File(path);
		exec();
	}

	public void exec() {
		FileReader fr = null;
		try {
			fr = new FileReader(f);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);

		StringBuffer sb = new StringBuffer();
		String eachLine = null;

		try {
			eachLine = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (eachLine != null) {
			sb.append(eachLine);

			try {
				eachLine = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		int size = sb.toString().split(":").length;
		String[] info = sb.toString().split(":");
		for (int i = 0; i < size; i++) {
			server = info[0];
			port = info[1];
			database = info[2];
			user = info[3];
			pass = info[4];
		}
	}

	public static void main(String[] argv) {
		DBPreferences p = new DBPreferences();
		System.out.println(p.user);
		System.out.println(p.pass);
		System.out.println(p.database);
		System.out.println(p.port);
		System.out.println(p.server);

		DBPreferences p2 = new DBPreferences("passwords2");
		System.out.println(p2.user);
		System.out.println(p2.pass);
		System.out.println(p2.database);
		System.out.println(p2.port);
		System.out.println(p2.server);

	}

}