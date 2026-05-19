package in.digeshwar.controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/orders")
public class ProtectedController {
    @GetMapping("/my")
    public List<String> orders(  @RequestHeader("X-Authenticated-User") String username) {

        return List.of(
                "Order for " + username,
                "Pizza",
                "Burger"
        );
    }
}
