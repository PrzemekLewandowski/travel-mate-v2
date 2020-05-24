package com.myapp.travelmate.service;

import com.myapp.travelmate.exception.UserAlreadyExistsException;
import com.myapp.travelmate.mapper.UserMapper;
import com.myapp.travelmate.model.Role;
import com.myapp.travelmate.model.User;
import com.myapp.travelmate.repository.UserRepository;
import com.myapp.travelmate.viewmodel.UserRegisterViewModel;
import com.myapp.travelmate.viewmodel.UserViewModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserViewModel> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper.INSTANCE::userViewModel)
                .collect(Collectors.toList());
    }

    public UserViewModel getUser(String id) {
        return UserMapper.INSTANCE.userViewModel(userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Didn't found user with id: %s.", id))));
    }

    @Transactional
    public UserViewModel addUser(UserRegisterViewModel userRegisterViewModel) {
        if (userRepository.findByUsernameOrEmail(userRegisterViewModel.getUsername(), userRegisterViewModel.getEmail())
                .isPresent()) {
            throw new UserAlreadyExistsException("User with given username or email already exists.");
        }

        User user = new User();
        user.setName(userRegisterViewModel.getName());
        user.setUsername(userRegisterViewModel.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterViewModel.getPassword()));
        user.setYearOfBirth(userRegisterViewModel.getYearOfBirth());
        user.setCity(userRegisterViewModel.getCity());
        user.setCreatedAt(LocalDateTime.now());
        user.setRoles(Collections.singleton(new Role("ROLE_USER")));
        userRepository.save(user);
        return UserMapper.INSTANCE.userViewModel(userRepository.findByUsername(userRegisterViewModel.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Problem with adding new user with username: %s", userRegisterViewModel.getUsername()))));
    }
}
