package in.digeshwar.Controller;

import in.digeshwar.Exception.UserNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Get {

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable Long id) {

        if(id == 10){
            throw new UserNotFoundException("User not found with id " + id);
        }
        return "User with id " + id + " found";
    }
}