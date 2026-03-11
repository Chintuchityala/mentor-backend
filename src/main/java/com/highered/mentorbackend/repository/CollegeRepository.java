package com.highered.mentorbackend.repository;

import com.highered.mentorbackend.model.College;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CollegeRepository extends MongoRepository<College, String> {
}
