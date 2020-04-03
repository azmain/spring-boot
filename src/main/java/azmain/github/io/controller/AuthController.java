package azmain.github.io.controller;

import azmain.github.io.domain.UserRegistration;
import azmain.github.io.domain.auth.AuthRequest;
import azmain.github.io.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(path = "login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest authRequest){
        return ResponseEntity.ok(authService.login(authRequest));
    }

    @PostMapping(path = "register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegistration userRegistration){
        return ResponseEntity.ok(authService.register(userRegistration));
    }
}
