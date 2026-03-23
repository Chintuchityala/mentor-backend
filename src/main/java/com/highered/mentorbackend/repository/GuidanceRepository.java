package com.highered.mentorbackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.highered.mentorbackend.model.GuidanceRequest;

public interface GuidanceRepository extends MongoRepository<GuidanceRequest, String> {
}