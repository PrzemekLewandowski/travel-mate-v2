package com.myapp.travelmate.viewmodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserViewModel {

    @JsonIgnore
    private final LocalDateTime editedAt = LocalDateTime.now();

    @NonNull
    @NotNull
    private String username;

    @NonNull
    @NotNull
    private String name;

    @NonNull
    @NotNull
    private String email;

    @NonNull
    @NotNull
    private String city;

    @NotNull
    private int yearOfBirth;

    private LocalDateTime preferredTravelDateFrom;

    private LocalDateTime preferredTravelDateTo;

    private int preferredBudgetValueFrom;

    private int preferredBudgetValueTo;

    private Set<String> preferredCountries;
}
