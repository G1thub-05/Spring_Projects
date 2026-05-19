package in.digeshwar.associationmapping.test;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)  // Exclude null values.
class Account {
    @JsonProperty("user_name") // Rename fields in JSON.
    private String username;

    @JsonIgnore              // Ignore field during serialization / deserialization.
    private String password; // will not appear in JSON
    private String email;    // null email will be excluded

    @JsonFormat(pattern = "yyyy-MM-dd") // Format dates.
    private LocalDate doj;

    public Account() {}
    public Account(String username, String password, String email, LocalDate doj) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.doj = doj;
    }


}

public class AnnotationsExample {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        // Register Java 8 Date/Time module
        mapper.registerModule(new JavaTimeModule());
        Account acc = new Account("digi", "secret", null, LocalDate.now());
        String json = mapper.writeValueAsString(acc);
        System.out.println(json); // {"user_name":"digi"}
    }
}
