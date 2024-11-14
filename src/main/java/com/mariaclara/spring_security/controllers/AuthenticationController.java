package com.mariaclara.spring_security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mariaclara.spring_security.entities.dtos.LoginRequestDTO;
import com.mariaclara.spring_security.entities.dtos.LoginResponseDTO;
import com.mariaclara.spring_security.entities.dtos.RegisterRequestDTO;
import com.mariaclara.spring_security.services.UserService;


@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO data) {
        String token = userService.authenticate(data);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDTO request) {
        userService.register(request);
        return ResponseEntity.ok().body("Usuário cadastrado com sucesso!");
    }
}
