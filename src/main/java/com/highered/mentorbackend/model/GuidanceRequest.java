package com.highered.mentorbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "guidance_requests")
public class GuidanceRequest {

    @Id
    private String id;

    private String name;
    private String phone;
    private String email;
    private String guidance;
    private String education;
    private String goal;

    public GuidanceRequest() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getGuidance() { return guidance; }
    public void setGuidance(String guidance) { this.guidance = guidance; }

    public String getEducation() { return education; }
    public void setEducation(String education) { this.education = education; }

    public String getGoal() { return goal; }
    public void setGoal(String goal) { this.goal = goal; }
}