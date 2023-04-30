package com.example.spring_mongo.repository;

import com.example.spring_mongo.model.JobCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobCategoryRepository  extends MongoRepository<JobCategory,String> {
    JobCategory findBySlug(String slug);
}
