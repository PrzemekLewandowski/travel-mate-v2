package com.myapp.travelmate.mapper;

import com.myapp.travelmate.model.Post;
import com.myapp.travelmate.viewmodel.PostViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PostMapper {

    public static final PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    public abstract PostViewModel postViewModel(Post post);

    public abstract Post post(PostViewModel postViewModel, @MappingTarget Post post);
}
