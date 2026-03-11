package com.highered.mentorbackend.repository;

import com.highered.mentorbackend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
     User findByEmail(String email);
}
