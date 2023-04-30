package com.example.spring_mongo.repository;

import com.example.spring_mongo.model.BlogCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogCategoryRepository extends MongoRepository<BlogCategory,String> {
    BlogCategory findBySlug(String slug);
}
