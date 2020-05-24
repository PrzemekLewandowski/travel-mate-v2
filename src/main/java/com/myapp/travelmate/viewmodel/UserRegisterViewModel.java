package com.myapp.travelmate.viewmodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterViewModel {

    private String username;

    private String name;

    private String password;

    private String email;

    private String city;

    private int yearOfBirth;
}
