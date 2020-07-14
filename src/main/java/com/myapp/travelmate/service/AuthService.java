package com.myapp.travelmate.service;

import com.myapp.travelmate.decorator.CreateTimestampDecorator;
import com.myapp.travelmate.exception.ResourceAlreadyExistsException;
import com.myapp.travelmate.mapper.UserMapper;
import com.myapp.travelmate.model.Role;
import com.myapp.travelmate.model.RoleEnum;
import com.myapp.travelmate.model.User;
import com.myapp.travelmate.repository.RoleRepository;
import com.myapp.travelmate.repository.UserRepository;
import com.myapp.travelmate.viewmodel.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseEntity<String> registerUser(SignUpRequest signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new ResourceAlreadyExistsException("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new ResourceAlreadyExistsException("Error: Email is already in use!");
        }

        Role roleUser = roleRepository
                .findByName(RoleEnum.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        User.Builder userBuilder = UserMapper.INSTANCE.userSignUpRequestToUser(signUpRequest);
        userBuilder.roles(Collections.singleton(roleUser));
        User user = userBuilder.build();
        CreateTimestampDecorator createTimestampDecorator = new CreateTimestampDecorator(user);
        createTimestampDecorator.setTimestamp();
        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
    }
}
