package in.digeshwar.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProtectedController {

    @GetMapping("/order")
    public List<String> orders(){
        return List.of("Order for ", "Pizza", "Burger");
    }
}
