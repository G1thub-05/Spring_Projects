package in.digeshwar.swaggerui;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/{id}")
    public ResponseEntity<String> getUser(@PathVariable int id) {
        return ResponseEntity.ok("User ID: " + id);
    }
}
