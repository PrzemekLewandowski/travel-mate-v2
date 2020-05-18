package com.myapp.travelmate.viewmodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myapp.travelmate.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class UserViewModel {

    @JsonIgnore
    private final LocalDateTime editedAt = LocalDateTime.now();

    @NonNull
    @NotNull
    private String username;
    @NonNull
    @NotNull
    private List<Role> roles;
}
