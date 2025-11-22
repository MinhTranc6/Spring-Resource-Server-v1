package minhtranc6.Spring_Resource_Server_v1.controllers;

import minhtranc6.Spring_Resource_Server_v1.entities.MyUser;
import minhtranc6.Spring_Resource_Server_v1.repositories.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {

    @Autowired
    private UserDetailRepository userDetailRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register/user")
    public MyUser createUser(@RequestBody MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassWord()));
        return userDetailRepository.save(user);
    }
}