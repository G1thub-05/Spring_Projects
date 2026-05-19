package in.digeshwar.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProtectedController {

    @GetMapping("/hello")
    public String hello() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return "You accessed a PROTECTED API using JWT";
    }
}
