package com.myapp.travelmate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myapp.travelmate.mapper.ObjectBuilder;
import lombok.Getter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Document(collection = "users")
public final class User extends AbstractDocument implements UserDetails {

    @Indexed
    private final String username;

    private final String name;

    private final String password;

    @Email
    private final String email;

    private final String city;

    private final int yearOfBirth;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDateTime preferredTravelDateFrom;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDateTime preferredTravelDateTo;

    private final int preferredBudgetValueFrom;

    private final int preferredBudgetValueTo;

    private final String description;

    private final String avatarFileName;

    private final Set<String> preferredCountries;

    @DBRef
    private final Set<Role> roles;

    private User(User.Builder builder) {
        this.username = builder.username;
        this.name = builder.name;
        this.password = builder.password;
        this.email = builder.email;
        this.city = builder.city;
        this.yearOfBirth = builder.yearOfBirth;
        this.preferredTravelDateFrom = builder.preferredTravelDateFrom;
        this.preferredTravelDateTo = builder.preferredTravelDateTo;
        this.preferredBudgetValueFrom = builder.preferredBudgetValueFrom;
        this.preferredBudgetValueTo = builder.preferredBudgetValueTo;
        this.description = builder.description;
        this.avatarFileName = builder.avatarFileName;
        this.preferredCountries = builder.preferredCountries;
        this.roles = builder.roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role
                        .getName()
                        .name()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static class Builder implements ObjectBuilder<User> {
        private String username;
        private String name;
        private String password;
        private String email;
        private String city;
        private int yearOfBirth;
        private LocalDateTime preferredTravelDateFrom = null;
        private LocalDateTime preferredTravelDateTo = null;
        private int preferredBudgetValueFrom = 0;
        private int preferredBudgetValueTo = 10000;
        private String description = "";
        private String avatarFileName = "";
        private Set<String> preferredCountries = Collections.emptySet();
        private Set<Role> roles = Collections.emptySet();

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder yearOfBirth(int yearOfBirth) {
            this.yearOfBirth = yearOfBirth;
            return this;
        }

        public Builder preferredTravelDateFrom(LocalDateTime preferredTravelDateFrom) {
            this.preferredTravelDateFrom = preferredTravelDateFrom;
            return this;
        }

        public Builder preferredTravelDateTo(LocalDateTime preferredTravelDateTo) {
            this.preferredTravelDateTo = preferredTravelDateTo;
            return this;
        }

        public Builder preferredBudgetValueFrom(int preferredBudgetValueFrom) {
            this.preferredBudgetValueFrom = preferredBudgetValueFrom;
            return this;
        }

        public Builder preferredBudgetValueTo(int preferredBudgetValueTo) {
            this.preferredBudgetValueTo = preferredBudgetValueTo;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder avatarFileName(String avatarFileName) {
            this.avatarFileName = avatarFileName;
            return this;
        }

        public Builder preferredCountries(Set<String> preferredCountries) {
            this.preferredCountries = preferredCountries;
            return this;
        }

        public Builder roles(Set<Role> roles) {
            this.roles = roles;
            return this;
        }


        @Override
        public User build() {
            return new User(this);
        }
    }
}
