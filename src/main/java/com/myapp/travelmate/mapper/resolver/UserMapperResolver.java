package com.myapp.travelmate.mapper.resolver;

import com.myapp.travelmate.model.User;
import com.myapp.travelmate.repository.UserRepository;
import com.myapp.travelmate.viewmodel.UserViewModel;
import lombok.RequiredArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;

@Component
@RequiredArgsConstructor
public class UserMapperResolver {

    private final UserRepository userRepository;

    @ObjectFactory
    User resolve(UserViewModel userViewModel, @TargetType Class<User> type) {
        return userViewModel != null && userViewModel.getId() != null ? userRepository.findByUsername(userViewModel.getUsername())
                .orElseThrow(() -> new InputMismatchException(String.format("Can't find user with id: %s.", userViewModel.getId()))) : new User();
    }

}
