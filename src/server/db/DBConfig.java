package server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import shared.Config;

public class DBConfig {
	static DBManager db = DBManager.getInstance();
	static Connection connection = db.getConnection();

	public static Config update(Config config) {
		String message = config.message();
		int status = db.updateSet("UPDATE FCONFIG SET WELCOME='" + message
				+ "'   WHERE ID='1'");
		if (status == 1) {
			return config;
		}
		return null;
	}

	public static Config get() {
		 
		try {
			ResultSet set = db.getSet("SELECT * FROM FCONFIG");

			set.next();
			Config conf = mapConfig(set);
			
			
			/* Do we need to sent client server stats? */
			//String m = conf.message(); 
			//conf.message(m.concat(HashMapToLine(serverStats())));
			
			// print stats on server?? > ; 
			displayServerStats();
			return conf;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public static void displayServerStats() throws SQLException {
		HashMapToLine(serverStats());
	}

	public static String HashMapToLine(HashMap<String, String> h) {
		String line = "\nSERVER STATS: ";

		Set set = h.entrySet();
		Iterator setIter = set.iterator();
		while (setIter.hasNext()) {
			 System.out.println(setIter.next());
			 //line.concat(setIter.next() + "\n");
			 
		}
 		return "";
	}
	public static HashMap<String, String> serverStats() throws SQLException {
		HashMap<String, String> map = new HashMap<String, String>();
		
		String[] key = {
		"Total comments",
		"Total thrads",
		"Total topics",
		"Total active topics",
		"Total not active topics",
		"Total users",
		"Total active users",
		"Total not active users",
		};
		
		String[] val = {
				"select count(*) as totalComments from fcomment",
				"select count(*) as totalThreads from fthread",
				"select count(*) as total from ftopic",
				"select count(*) as active from ftopic where isactive ='true'",
				"select count(*) as notactive from ftopic where isactive ='false'",
				"select count(*) as total from fuser",
				"select count(*) as notactive from fuser where isactive ='false'",
				"select count(*) as active from fuser where isactive ='true'",
		};

		
			for (int i = 0; i < key.length; i++) {
			map.put(key[i], getQuery(val[i]));
		}

		//return map.toString();
		return map;

	}

	
	private static String getQuery(String q) throws SQLException {
		try {
			ResultSet set = db.getSet(q);
			set.next();
			return set.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	private static Config mapConfig(ResultSet set) throws SQLException {
		return new Config(set.getString("WELCOME"));

	}

}
