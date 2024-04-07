package com.cogent.ecom.rest.api.service.impl;

import com.cogent.ecom.rest.api.entity.Role;
import com.cogent.ecom.rest.api.entity.User;
import com.cogent.ecom.rest.api.payload.LoginDto;
import com.cogent.ecom.rest.api.payload.RegisterDto;
import com.cogent.ecom.rest.api.repository.RoleRepository;
import com.cogent.ecom.rest.api.repository.UserRepository;
import com.cogent.ecom.rest.api.security.JwtTokenProvider;
import com.cogent.ecom.rest.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {
        // add check for username exists in database
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new RuntimeException("username is already exists!.");
        }
        // add check for email exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new RuntimeException("email is already exists!.");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);
        return "user registered successfully";
    }
}

