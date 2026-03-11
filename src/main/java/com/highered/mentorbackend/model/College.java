package com.highered.mentorbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "colleges")
public class College {

    @Id
    private String id;
    private String name;
    private String location;
    private String type;
    private List<String> coursesOffered;
    private String description;
    private String merits;
    private String demerits;
    private double rating;
    private String reviews;
     

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getCoursesOffered() {
        return coursesOffered;
    }

    public void setCoursesOffered(List<String> coursesOffered) {
        this.coursesOffered = coursesOffered;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMerits() {
       return merits;
    }

    public void setMerits(String merits) {
       this.merits = merits;
    }

    public String getDemerits() {
       return demerits;
    } 

    public void setDemerits(String demerits) {
       this.demerits = demerits;
    }

    public double getRating() {
       return rating;
    }

    public void setRating(double rating) {
       this.rating = rating;
    }

    public String getReviews() {
       return reviews;
    }

    public void setReviews(String reviews) {
       this.reviews = reviews;
    }
}