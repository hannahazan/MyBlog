package org.wildcodeschool.MyBlogApplication.controller;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wildcodeschool.MyBlogApplication.dto.UserRegistrationDto;
import org.wildcodeschool.MyBlogApplication.model.User;
import org.wildcodeschool.MyBlogApplication.service.UserService;

import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRegistrationDto userRegistrationDto){
        User registeredUser = userService.registerUser(
                userRegistrationDto.getEmail(),
                userRegistrationDto.getPassword(),
                Set.of("ROLE_USER")//par défaut, chaque utilisateur a le rôle USER
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

}
