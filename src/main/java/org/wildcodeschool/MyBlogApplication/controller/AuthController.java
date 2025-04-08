package org.wildcodeschool.MyBlogApplication.controller;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wildcodeschool.MyBlogApplication.dto.UserLoginDTO;
import org.wildcodeschool.MyBlogApplication.dto.UserRegistrationDto;
import org.wildcodeschool.MyBlogApplication.model.User;
import org.wildcodeschool.MyBlogApplication.security.AuthenticationService;
import org.wildcodeschool.MyBlogApplication.service.UserService;

import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    public AuthController(UserService userService, AuthenticationService authenticationService){
        this.userService = userService;
        this.authenticationService = authenticationService;
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

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody UserLoginDTO userLoginDTO) {
        String token = authenticationService.authenticate(
                userLoginDTO.getEmail(),
                userLoginDTO.getPassword()
        );
        return ResponseEntity.ok(token);
    }

}
