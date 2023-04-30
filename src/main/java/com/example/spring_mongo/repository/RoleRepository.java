package com.example.spring_mongo.repository;

import com.example.spring_mongo.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role,String> {
    Role findByAlias(String alias);
}
