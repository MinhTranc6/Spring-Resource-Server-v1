package minhtranc6.Spring_Resource_Server_v1.controllers;

import minhtranc6.Spring_Resource_Server_v1.services.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingsController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailService userDetailService;

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

//    @PostMapping("/authenticate")
//    public String authenticateAndGetToken(@RequestBody LoginForm loginForm) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                loginForm.userName(), loginForm.passWord()
//        ));
//        if (authentication.isAuthenticated()){
//            return jwtService.generateToken(userDetailService.loadUserByUsername(loginForm.userName()));
//        } else {
//            throw new UsernameNotFoundException("Invalid credentials");
//        }
//    }

}
