package in.digeshwar.controller;

import in.digeshwar.entity.UserEntity;
import in.digeshwar.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // SHOW REGISTER PAGE
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    // HANDLE REGISTER
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password) {

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return "redirect:/login"; // Spring Default login
    }



    // PLAIN TEXT RESPONSE AFTER LOGIN
    @GetMapping("/welcome")
    @ResponseBody
    public String welcomePage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepository.findByUsername(auth.getName()).orElseThrow();
        String username = auth.getName();
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        return "welcome: " + username + " " + user + " " + isAdmin; // // PLAIN TEXT RESPONSE AFTER LOGIN not .html
    }
}
