package com.myapp.travelmate.mapper;

import com.myapp.travelmate.model.User;
import com.myapp.travelmate.viewmodel.UserViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

//@Mapper(uses = {UserMapperResolver.class})
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {

    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public abstract UserViewModel userViewModel(User user);

    public abstract User user(UserViewModel userViewModel, @MappingTarget User user);

    // TODO: might be useful in the future
    ///   User user(UserViewModel userViewModel, @MappingTarget User user); //NOSONAR
}
