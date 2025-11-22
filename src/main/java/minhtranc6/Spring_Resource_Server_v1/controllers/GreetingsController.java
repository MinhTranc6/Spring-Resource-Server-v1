package minhtranc6.Spring_Resource_Server_v1.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingsController {

    @GetMapping("/greetings")
    public String greet() {
        return "Welcome to the resource server!";
    }

    @GetMapping("/user/greetings")
    public String handleUser() {
        return "Welcome" + " to your page!";
    }

    @GetMapping("/admin/greetings")
    public String handleAdmin() {
        return "Welcome, Administrator!";
    }

}
