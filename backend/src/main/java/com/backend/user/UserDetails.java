package com.backend.user;

import javax.validation.constraints.Size;
import java.util.List;

public class UserDetails {
    private String email; // To identify the user
    private  String name;
    private List<AreaOfInterest> interests;
    private List<String> skills;
    @Size(max = 50, message = "Best project description must be 50 characters or less")
    private String bestProjectDescription;
    private String s_key;

    public String getS_key() {
        return s_key;
    }

    public void setS_key(String s_key) {
        this.s_key = s_key;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AreaOfInterest> getInterests() {
        return interests;
    }

    public void setInterests(List<AreaOfInterest> interests) {
        this.interests = interests;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getBestProjectDescription() {
        return bestProjectDescription;
    }

    public void setBestProjectDescription(String bestProjectDescription) {
        this.bestProjectDescription = bestProjectDescription;
    }
}
