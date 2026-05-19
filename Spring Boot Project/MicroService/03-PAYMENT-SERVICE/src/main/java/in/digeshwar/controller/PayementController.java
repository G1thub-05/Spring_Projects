package in.digeshwar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PayementController {

    @GetMapping("/pay")
    public String pay(){
        return "Payment Success";
    }
}
