package travelmate.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.myapp.travelmate.mapper.UserMapper;
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
    private static Role role;
    private static Role changedRole;
    private static UserViewModel mappedUserViewModel;
    private static UserViewModel userViewModel;

    @BeforeAll
    static void setup() {
        role = new Role();
        role.setName("ROLE_TEST");
        changedRole = new Role();
        changedRole.setName("ROLE_CHANGED_TEST");

        user = new User();
        user.setUsername("name");
        user.setPassword("password");
        user.setId(1L);
        user.setRoles(Collections.singletonList(role));

        mappedUser = new User();
        mappedUser.setUsername("name");
        mappedUser.setPassword("password");
        mappedUser.setId(1L);
        mappedUser.setRoles(Stream.of(role)
                .collect(Collectors.toList()));

        userViewModel = new UserViewModel();
        userViewModel.setRoles(Stream.of(changedRole)
                .collect(Collectors.toList()));
        userViewModel.setUsername("changed name");

        mappedUserViewModel = UserMapper.INSTANCE.userViewModel(user);
        mappedUser = UserMapper.INSTANCE.user(userViewModel, mappedUser);
    }

    @Test
    void shouldMapUsernameToUserViewModel() {
        assertThat(user.getUsername()).isEqualTo(mappedUserViewModel.getUsername());
    }


    @Test
    void shouldMapUserRolesToUserViewModel() {
        assertThat(user.getRoles()
                .get(0)
                .getName()).isEqualTo(mappedUserViewModel.getRoles()
                .get(0)
                .getName());
    }

    @Test
    void shouldChangeEditedTimeForUserViewModel() {
        assertThat(mappedUserViewModel.getEditedAt()).isNotNull();
    }

    @Test
    void shouldMapUsernameToUserEntity() {
        assertThat(userViewModel.getUsername()).isEqualTo(mappedUser.getUsername());
    }

    @Test
    void shouldMapRolesToUserEntity() {
        assertThat(userViewModel.getRoles()
                .get(0)
                .getName()).isEqualTo(mappedUser.getRoles()
                .get(0)
                .getName());
    }

    @Test
    void shouldChangeEditedTimeForUserEntity() {
        assertThat(userViewModel.getEditedAt()).isEqualTo(mappedUser.getEditedAt());
    }

    @Test
    void shouldNotChangeIdForUserEntity() {
        assertThat(mappedUser.getId()).isEqualTo(1L);
    }

    @Test
    void shouldNotChangePasswordForUserEntity() {
        assertThat(mappedUser.getPassword()).isEqualTo("password");
    }

}
