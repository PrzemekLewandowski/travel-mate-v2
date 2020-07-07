package com.myapp.travelmate.controller;

import com.myapp.travelmate.service.PostService;
import com.myapp.travelmate.viewmodel.PostViewModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public List<PostViewModel> getPosts(){
        return postService.getPosts();
    }
}
