package in.digeshwar.associationmapping.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
class User {
    public String name;
    public boolean active;

    // Default constructor needed by Jackson
    public User() {}
    public User(String name, boolean active) {
        this.name = name;
        this.active = active;
    }
}

public class Databind {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // 🍁 Serialize: Convert Java Object → JSON
        User user = new User("Digeshwar", true);
        String json = mapper.writeValueAsString(user);
        System.out.println("JSON: " + json);

        // 🍁 Deserialize: Convert JSON → Java Object
        User fromJson = mapper.readValue(json, User.class);
        System.out.println("User: " + fromJson);
    }
}


