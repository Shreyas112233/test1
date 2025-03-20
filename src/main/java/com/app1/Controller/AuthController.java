package com.app1.Controller;

import com.app1.entity.User;
import com.app1.repostitory.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @PostMapping("/signup")
    public ResponseEntity<?> createUser(
            @RequestBody User user
    ){
        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if(opUsername.isPresent()){
            return new ResponseEntity<>("username already exist",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User> opEmail = userRepository.findByEmailId(user.getEmailId());
        if (opEmail.isPresent()) {
            return new ResponseEntity<>("email already exist", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        userRepository.save(user);
        return new ResponseEntity<>("Created",HttpStatus.CREATED);
    }
    @GetMapping("/getmessage")
    public String getMessage(){
        return " hello";
    }
}