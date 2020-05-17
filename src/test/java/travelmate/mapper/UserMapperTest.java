package travelmate.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.myapp.travelmate.mapper.UserMapper;
import com.myapp.travelmate.model.Role;
import com.myapp.travelmate.model.User;
import com.myapp.travelmate.viewmodel.UserViewModel;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
class UserMapperTest {

    @Test
    void shouldMapUserToUserViewModel() {
        //given
        Role role = new Role();
        role.setName("ROLE_TEST");

        User user = new User();
        user.setUsername("name");
        user.setPassword("password");
        user.setId(1L);
        user.setRoles(Collections.singletonList(role));

        //when
        UserViewModel userViewModel = UserMapper.INSTANCE.userViewModel(user);

        //then
        assertThat(userViewModel).isNotNull();
        assertThat(userViewModel.getUsername()).isEqualTo("name");
        assertThat(userViewModel.getId()).isEqualTo(1L);
        assertThat(userViewModel.getRoles()
                .size()).isEqualTo(1);
        assertThat(userViewModel.getRoles()
                .get(0)
                .getName()).isEqualTo("ROLE_TEST");
        assertThat(userViewModel.getEditedAt()).isNotNull();
    }

    @Test
    void shouldMapUserViewModelToUser() {
        //given
        Role role = new Role();
        role.setName("ROLE_USER");

        User user = new User();
        user.setUsername("name");
        user.setRoles(Stream.of(role)
                .collect(Collectors.toList()));
        user.setPassword("password");
        user.setId(1L);

        Role changedRole = new Role();
        changedRole.setName("ROLE_ADMIN");

        UserViewModel userViewModel = new UserViewModel();
        userViewModel.setId(2L);
        userViewModel.setRoles(Stream.of(changedRole)
                .collect(Collectors.toList()));
        userViewModel.setUsername("changed name");

        //when
        user = UserMapper.INSTANCE.user(userViewModel, user);

        //then
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("changed name");
        assertThat(user.getPassword()).isEqualTo("password");
        assertThat(user.getId()).isEqualTo(2L);
        assertThat(user.getRoles()
                .size()).isEqualTo(1);
        assertThat(user.getRoles()
                .get(0)
                .getName()).isEqualTo("ROLE_ADMIN");
        assertThat(user.getEditedAt()).isEqualTo(userViewModel.getEditedAt());
    }

}
