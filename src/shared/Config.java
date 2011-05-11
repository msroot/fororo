package shared;

import java.util.*;

/**
 * Configuration Class
 * @author Victor Nava
 *
 */
public class Config implements java.io.Serializable {
	private String message = null;

	public Config() {
	};

	/**
	 * 
	 * @param message
	 */
	public Config(String message) {
		this.message = message;
	}

	public String message() {
		return message;
	}

	public void message(String message) {
		this.message = message;
	}
}