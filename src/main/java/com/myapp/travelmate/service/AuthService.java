package com.myapp.travelmate.service;

import com.myapp.travelmate.model.Role;
import com.myapp.travelmate.model.RoleEnum;
import com.myapp.travelmate.model.User;
import com.myapp.travelmate.payload.response.MessageResponse;
import com.myapp.travelmate.repository.RoleRepository;
import com.myapp.travelmate.repository.UserRepository;
import com.myapp.travelmate.viewmodel.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.logging.Logger;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseEntity<?> registerUser(SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User();
        Role roleUser = roleRepository.findByName(RoleEnum.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        user.setName(signUpRequest.getName());
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setYearOfBirth(signUpRequest.getYearOfBirth());
        user.setCity(signUpRequest.getCity());
        user.setCreatedAt(LocalDateTime.now());
        user.setRoles(Collections.singleton(roleUser));
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
