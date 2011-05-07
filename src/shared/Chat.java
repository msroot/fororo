package shared;

import java.util.HashMap;

public class Chat implements java.io.Serializable {
	public HashMap<User, Topic> broadCast = null;

	public Chat() {
		broadCast = new HashMap<User, Topic>();
	}

	public Chat broadCast(User user) {
		return null;
	}

	public HashMap update(User user, Topic topic) {
		if (remove(user) && add(user, topic)) {
			return broadCast;
		}
		return null;

	}

	public boolean remove(User user) {
		return broadCast.remove(user) != null;

	}

	public boolean add(User user, Topic topic) {
		return broadCast.put(user, topic) != null;
	}
}
