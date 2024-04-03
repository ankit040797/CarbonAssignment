package com.carbon.Carbon.controller;


import com.carbon.Carbon.model.AuthResponse;
import com.carbon.Carbon.model.User;
import com.carbon.Carbon.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.Authenticator;
@RestController
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthController {
    @Autowired
    AuthService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody User request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody User request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
