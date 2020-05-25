package com.myapp.travelmate.service;

import com.myapp.travelmate.exception.UserAlreadyExistsException;
import com.myapp.travelmate.mapper.UserMapper;
import com.myapp.travelmate.model.Role;
import com.myapp.travelmate.model.RoleEnum;
import com.myapp.travelmate.model.User;
import com.myapp.travelmate.repository.UserRepository;
import com.myapp.travelmate.viewmodel.SignUpRequest;
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

    public List<UserViewModel> getUsers() {
        return userRepository.findAll().stream().map(UserMapper.INSTANCE::userViewModel).collect(Collectors.toList());
    }

    public UserViewModel getUser(String id) {
        return UserMapper.INSTANCE.userViewModel(userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(String.format("Didn't found user with id: %s.", id))));
    }
}
