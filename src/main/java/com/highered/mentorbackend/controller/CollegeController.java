package com.highered.mentorbackend.controller;

import com.highered.mentorbackend.model.College;
import com.highered.mentorbackend.repository.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colleges")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class CollegeController {

    @Autowired
    private CollegeRepository collegeRepository;

    @GetMapping
    public List<College> getAllColleges() {
        return collegeRepository.findAll();
    }
}

