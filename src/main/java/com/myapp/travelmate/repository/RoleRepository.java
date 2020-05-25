package com.myapp.travelmate.repository;

import com.myapp.travelmate.model.Role;
import com.myapp.travelmate.model.RoleEnum;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {

    Optional<Role> findByName(RoleEnum name);
}
