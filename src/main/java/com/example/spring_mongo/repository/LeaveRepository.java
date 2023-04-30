package com.example.spring_mongo.repository;

import com.example.spring_mongo.model.Leave;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface LeaveRepository extends MongoRepository<Leave, String> {
}
