package com.myapp.travelmate.repository;

import com.myapp.travelmate.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {

}
