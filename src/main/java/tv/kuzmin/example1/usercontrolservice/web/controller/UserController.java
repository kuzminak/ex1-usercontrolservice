package tv.kuzmin.example1.usercontrolservice.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/")
    public String view(){
        return "users";
    }
}
