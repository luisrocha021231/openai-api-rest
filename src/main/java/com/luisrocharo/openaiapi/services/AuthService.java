package com.luisrocharo.openaiapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.luisrocharo.openaiapi.dto.NewUser;
import com.luisrocharo.openaiapi.entities.RoleEntity;
import com.luisrocharo.openaiapi.entities.UserEntity;
import com.luisrocharo.openaiapi.enums.RoleList;
import com.luisrocharo.openaiapi.jwt.JwtUtil;
import com.luisrocharo.openaiapi.repositories.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String authenticate(String username, String password) {
        Authentication authResult = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authResult);

        return jwtUtil.generateToken(authResult);
    }

    public void registerUser(NewUser newUser) {
        if (userService.existsByUsername(newUser.getUsername())) {
            throw new RuntimeException("Username is already taken");
        }

        RoleEntity roleUser = roleRepository.findByName(RoleList.ROLE_USER)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        UserEntity user = new UserEntity(newUser.getUsername(), newUser.getPassword(), newUser.getEmail(), roleUser);
        userService.createUser(user);
    }
}
