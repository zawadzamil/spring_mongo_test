package com.example.spring_mongo.repository;

import com.example.spring_mongo.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends MongoRepository<Job,String> {
    Job findBySlug(String slug);

    Page<Job> findByActive(boolean b, PageRequest pageRequest);
}
