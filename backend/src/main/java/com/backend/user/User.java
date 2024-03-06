package com.backend.user;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Document(collection = "users")
public class User implements Serializable {

    @Id
    private String id;
    private String email;
    private String password;
    private UserType userType;
    private  String name;
    private List<AreaOfInterest> interests;
    private List<String> skills;
    @Size(max = 50, message = "Best project description must be 50 characters or less")
    private String bestProjectDescription;
    private String company;
    private String companyDescription;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserType getUserType()
    {
        return userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany() {
        return company;
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

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}