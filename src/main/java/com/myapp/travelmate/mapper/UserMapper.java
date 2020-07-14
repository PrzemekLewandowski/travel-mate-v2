package com.myapp.travelmate.mapper;

import com.myapp.travelmate.model.User;
import com.myapp.travelmate.viewmodel.SignUpRequest;
import com.myapp.travelmate.viewmodel.UserViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {

    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    abstract User.Builder userViewModelToUserBuilder(UserViewModel userViewModel);

    abstract User.Builder userSignUpRequestToUserBuilder(SignUpRequest signUpRequest);

    public abstract UserViewModel userViewModel(User user);

//    public abstract User user(UserViewModel userViewModel, @MappingTarget User user);
//
//    public abstract User user(SignUpRequest signUpRequest);

    public User userViewModelToUser(UserViewModel userViewModel) {
        User.Builder builder = userViewModelToUserBuilder(userViewModel);
        return builder != null ?
                builder.build() :
                null;
    }

    public User.Builder userSignUpRequestToUser(SignUpRequest signUpRequest) {
        User.Builder builder = userSignUpRequestToUserBuilder(signUpRequest);
        return builder ;
    }
}
