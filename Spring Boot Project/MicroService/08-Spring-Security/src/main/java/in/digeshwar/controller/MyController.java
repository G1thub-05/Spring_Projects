package in.digeshwar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/public/get")
    public String GetPublicString () {
        return "return from GetPublicString";
    }

    @GetMapping("/admin/get")
    public String GetAdminString () {
        return "return from GetAdminString";
    }

    @GetMapping("/api/get")
    public String GetHomeString () {
        return "return from GetAPIString";
    }

    @GetMapping("/get")
    public String GetString () {
        return "return from GetString";
    }
}
