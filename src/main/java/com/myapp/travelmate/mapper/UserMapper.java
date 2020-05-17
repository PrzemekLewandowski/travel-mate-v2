package com.myapp.travelmate.mapper;

import com.myapp.travelmate.model.User;
import com.myapp.travelmate.viewmodel.UserViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

//@Mapper(uses = {UserMapperResolver.class})
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserViewModel userViewModel(User user);

    User user(UserViewModel userViewModel, @MappingTarget User user);

    //    User user(UserViewModel userViewModel, @MappingTarget User user);
}
