package com.mariaclara.spring_security.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mariaclara.spring_security.entities.User;
import com.mariaclara.spring_security.entities.dtos.LoginRequestDTO;
import com.mariaclara.spring_security.entities.dtos.RegisterRequestDTO;
import com.mariaclara.spring_security.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public String authenticate(LoginRequestDTO data) {
        var user = userRepository.findByUsername(data.username());

        if (user.isEmpty() || !isLoginCorrect(data, user.get())) {
            throw new BadCredentialsException("Usuário e/ou senha inválidos!");
        }

        return tokenService.generateToken(user.get());
    }

    public boolean isLoginCorrect(LoginRequestDTO data, User user) {
        return passwordEncoder.matches(data.password(), user.getPassword());
    }

    public void register(RegisterRequestDTO data) {
        Optional<User> user = userRepository.findByUsername(data.username());

        if (user.isPresent()) {
            throw new BadCredentialsException("Username já utilizado por outro usuário!");
        }

        String encodedPassword = passwordEncoder.encode(data.password());
    
        User newUser = new User(data.username(), encodedPassword, Set.of(data.roles()));
        userRepository.save(newUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() ->  new EntityNotFoundException("Usuário não encontrado!"));
    }

}
