package com.myapp.travelmate.service;

import com.myapp.travelmate.exception.ResourceNotFoundException;
import com.myapp.travelmate.mapper.UserMapper;
import com.myapp.travelmate.repository.UserRepository;
import com.myapp.travelmate.viewmodel.UserViewModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserViewModel> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper.INSTANCE::userViewModel)
                .collect(Collectors.toList());
    }

    public UserViewModel getUser(String id) {
        return UserMapper.INSTANCE.userViewModel(userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Could not found user with id: %s.", id))));
    }
}
