package in.digeshwar.gson;
import com.google.gson.Gson;

class User {
	String name;
	boolean active;

	User(String name, boolean active) {
		this.name = name;
		this.active = active;
	}
}

public class GsonDataBinding  {
	public static void main(String[] args) {
		Gson gson = new Gson();

		// Java → JSON
		User user = new User("Digeshwar", true);
		String json = gson.toJson(user);
		System.out.println(json);

		// JSON → Java
		User fromJson = gson.fromJson(json, User.class);
		System.out.println(fromJson.name + " " + fromJson.active);
	}
}