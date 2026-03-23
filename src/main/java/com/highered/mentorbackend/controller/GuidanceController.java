package com.highered.mentorbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.highered.mentorbackend.model.GuidanceRequest;
import com.highered.mentorbackend.repository.GuidanceRepository;

@RestController
@RequestMapping("/api/guidance")
@CrossOrigin(origins = "http://localhost:3000")
public class GuidanceController {

    @Autowired
    private GuidanceRepository repository;

    @PostMapping
    public GuidanceRequest saveGuidance(@RequestBody GuidanceRequest request) {
        return repository.save(request);
    }
}