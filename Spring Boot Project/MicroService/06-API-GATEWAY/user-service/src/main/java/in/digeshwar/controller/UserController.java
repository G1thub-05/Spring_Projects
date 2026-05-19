package in.digeshwar.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/profile")
    public String profile() {
        return "User Profile Data";
    }
}
