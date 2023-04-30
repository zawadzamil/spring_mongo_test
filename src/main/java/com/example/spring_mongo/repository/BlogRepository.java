package com.example.spring_mongo.repository;

import com.example.spring_mongo.enums.StatusEnum;
import com.example.spring_mongo.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends MongoRepository<Blog,String> {
    Blog findBySlug(String slug);

    Page<Blog> findByStatus(StatusEnum statusEnum, PageRequest pageRequest);
}
