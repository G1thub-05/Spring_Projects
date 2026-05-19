import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class welcom {
    @GetMapping("/welcome")
    public String welcome(){
        System.out.println("Welcome to Swagger UI");
        return "Hello User";
    }
}
