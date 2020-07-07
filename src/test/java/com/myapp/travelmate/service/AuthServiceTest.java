package com.myapp.travelmate.service;

import com.myapp.travelmate.exception.ResourceAlreadyExistsException;
import com.myapp.travelmate.model.Role;
import com.myapp.travelmate.model.RoleEnum;
import com.myapp.travelmate.repository.RoleRepository;
import com.myapp.travelmate.repository.UserRepository;
import com.myapp.travelmate.viewmodel.SignUpRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@Transactional
@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    private static SignUpRequest signUpRequest;

    @BeforeAll
    static void initialize() {
        signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("username");
        signUpRequest.setPassword("password");
        signUpRequest.setCity("city");
        signUpRequest.setEmail("email@gmail.com");
        signUpRequest.setName("name");
    }

    @Test
    void shouldReturnResourceAlreadyExistsExceptionWhenUserExistsByUsername() {
        //given
        when(userRepository.existsByUsername(signUpRequest.getUsername())).thenReturn(true);

        //when
        assertThatThrownBy(() -> authService.registerUser(signUpRequest))
                .isInstanceOf(ResourceAlreadyExistsException.class)
                .hasMessage("Error: Username is already taken!");
    }

    @Test
    void shouldReturnResourceAlreadyExistsExceptionWhenExistsByEmail() {
        //given
        when(userRepository.existsByEmail(signUpRequest.getEmail())).thenReturn(true);

        //when
        assertThatThrownBy(() -> authService.registerUser(signUpRequest))
                .isInstanceOf(ResourceAlreadyExistsException.class)
                .hasMessage("Error: Email is already in use!");
    }

    @Test
    void shouldReturnResponseEntityWhenUserSuccessfullyRegistered() {
        //given
        when(userRepository.existsByUsername(signUpRequest.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(signUpRequest.getEmail())).thenReturn(false);
        when(roleRepository.findByName(RoleEnum.ROLE_USER)).thenReturn(Optional.of(new Role()));
        //when
        ResponseEntity<String> responseEntity = authService.registerUser(signUpRequest);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isEqualTo("User registered successfully!");
    }
}