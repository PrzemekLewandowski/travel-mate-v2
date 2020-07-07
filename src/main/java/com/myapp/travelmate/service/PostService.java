package com.myapp.travelmate.service;

import com.myapp.travelmate.mapper.PostMapper;
import com.myapp.travelmate.repository.PostRepository;
import com.myapp.travelmate.viewmodel.PostViewModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<PostViewModel> getPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(PostMapper.INSTANCE::postViewModel)
                .collect(Collectors.toList());
    }
}
