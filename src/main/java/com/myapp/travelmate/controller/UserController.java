package com.myapp.travelmate.controller;

import com.myapp.travelmate.viewmodel.UserViewModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    public UserViewModel getUser(){
        return null;
    }
}