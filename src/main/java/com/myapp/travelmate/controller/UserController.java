package com.myapp.travelmate.controller;

import com.myapp.travelmate.service.UserService;
import com.myapp.travelmate.viewmodel.SignUpRequest;
import com.myapp.travelmate.viewmodel.UserViewModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UserViewModel> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public UserViewModel getUser(@PathVariable String id) {
        return userService.getUser(id);
    }
}
