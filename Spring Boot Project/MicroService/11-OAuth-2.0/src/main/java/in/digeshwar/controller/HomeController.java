package in.digeshwar.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "index";
    }
}
@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin")
class AdminController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Admin Dashboard";
    }
}

@RestController
@RequestMapping("/public")
class PublicController {
    @GetMapping("/hello")
    public String hello() {
        return "Public API";
    }
}

@RestController
@RequestMapping("/api")
class ApiController {

    @GetMapping("/profile")
    public String profile(OAuth2AuthenticationToken auth){
        OAuth2User user = auth.getPrincipal();
        String name = user.getAttribute("name");
        String email = user.getAttribute("email");
        return "Hello " + name + " (" + email + ")";
    }
}


