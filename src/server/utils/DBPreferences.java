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
	 * <DBPreferences> Class is responsible to read a file from disk and and
	 * adds values to user,pass etc File must have a single line with 5 values
	 * Example: server:port:database:user:pass
	 */
	public DBPreferences() {
		this.f = new File("passwords");
		exec();
	}

	/**
	 * Same as above overloading for reading different file
	 * 
	 * @param path
	 */
	public DBPreferences(String path) {
		this.f = new File(path);
		exec();
	}

	/**
	 * Read the given file and split it to 5 values. 
	 * After adds each value to
	 * user,pass server etc
	 */
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

}