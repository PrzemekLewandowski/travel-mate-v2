package com.myapp.travelmate.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.myapp.travelmate.model.Country;
import com.myapp.travelmate.model.Role;
import com.myapp.travelmate.model.User;
import com.myapp.travelmate.viewmodel.UserViewModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
class UserMapperTest {

    private static User user;
    private static User mappedUser;
    private static UserViewModel userViewModel;
    private static UserViewModel mappedUserViewModel;
    private static Role role;

    @BeforeAll
    static void setUp() {
        role = new Role();
        role.setName("ROLE_TEST");

        Country country = new Country();
        country.setName("country");

        Country changedCountry = new Country();
        country.setName("changed Country");

        user = new User();
        user.setId(1L);
        user.setUsername("username");
        user.setName("name");
        user.setPassword("password");
        user.setCity("city");
        user.setYearOfBirth(1994);
        user.setRoles(Collections.singleton(role));
        user.setPreferredCountries(Collections.singleton(country));


        mappedUser = new User();
        mappedUser.setId(2L);
        mappedUser.setUsername("username");
        mappedUser.setName("name");
        mappedUser.setPassword("password");
        mappedUser.setCity("city");
        mappedUser.setYearOfBirth(1990);
        mappedUser.setRoles(Stream.of(role).collect(Collectors.toSet()));
        mappedUser.setPreferredCountries(Stream.of(country).collect(Collectors.toSet()));

        userViewModel = new UserViewModel();
        userViewModel.setUsername("changed username");
        userViewModel.setName("changed name");
        userViewModel.setCity("changed city");
        userViewModel.setYearOfBirth(1969);
        userViewModel.setPreferredCountries(Stream.of(changedCountry).collect(Collectors.toSet()));

        mappedUserViewModel = UserMapper.INSTANCE.userViewModel(user);
        mappedUser = UserMapper.INSTANCE.user(userViewModel, mappedUser);
    }

    @Test
    void shouldMapUsernameToUserViewModel() {
        assertThat(user.getUsername()).isEqualTo(mappedUserViewModel.getUsername());
    }

    @Test
    void shouldMapNameToUserViewModel() {
        assertThat(user.getName()).isEqualTo(mappedUserViewModel.getName());
    }

    @Test
    void shouldMapCityToUserViewModel() {
        assertThat(user.getCity()).isEqualTo(mappedUserViewModel.getCity());
    }

    @Test
    void shouldMapYearOfBirthToUserViewModel() {
        assertThat(user.getYearOfBirth()).isEqualTo(mappedUserViewModel.getYearOfBirth());
    }

    @Test
    void shouldMapPreferredCountriesToUserViewModel() {
        assertThat(user.getPreferredCountries()).isEqualTo(mappedUserViewModel.getPreferredCountries());
    }

    @Test
    void shouldSetEditingTimeForUserViewModel() {
        assertThat(mappedUserViewModel.getEditedAt()).isNotNull();
    }

    @Test
    void shouldMapUsernameToUserEntity() {
        assertThat(userViewModel.getUsername()).isEqualTo(mappedUser.getUsername());
    }

    @Test
    void shouldMapNameToUserEntity() {
        assertThat(userViewModel.getName()).isEqualTo(mappedUser.getName());
    }

    @Test
    void shouldMapCityToUserEntity() {
        assertThat(userViewModel.getCity()).isEqualTo(mappedUser.getCity());
    }

    @Test
    void shouldMapYearOfBirthToUserEntity() {
        assertThat(userViewModel.getYearOfBirth()).isEqualTo(mappedUser.getYearOfBirth());
    }

    @Test
    void shouldChangeEditingTimeForUserEntity() {
        assertThat(userViewModel.getEditedAt()).isEqualTo(mappedUser.getEditedAt());
    }

    @Test
    void shouldChangePreferredCountriesForUserEntity() {
        assertThat(userViewModel.getPreferredCountries()).isEqualTo(mappedUser.getPreferredCountries());
    }

    @Test
    void shouldNotChangeRolesForUserEntity() {
        assertThat(mappedUser.getRoles().contains(role) && mappedUser.getRoles().size() == 1);
    }

    @Test
    void shouldNotChangeIdForUserEntity() {
        assertThat(mappedUser.getId()).isEqualTo(2L);
    }

    @Test
    void shouldNotChangePasswordForUserEntity() {
        assertThat(mappedUser.getPassword()).isEqualTo("password");
    }

}
