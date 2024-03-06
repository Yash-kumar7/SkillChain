package com.backend.controller;

import com.backend.user.RecruiterDetails;
import com.backend.user.User;
import com.backend.user.UserDetails;
import com.backend.service.UserService;
import com.backend.user.UserType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserDetailsController {

    @Autowired
    private UserService userService;
    @Autowired
    private  RippleIntegration rippleIntegration;

    @PostMapping("/jobSeekerDetails")
    public ResponseEntity<String> updateUserDetails(@RequestBody UserDetails userDetails) {
        try {
            // Check if the user exists based on the email
            User existingUser = userService.findByEmail(userDetails.getEmail());

            if (existingUser == null) {
                return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
            }
            String s_key=rippleIntegration.makeAccountRequest();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(s_key);
            String sKey = jsonNode.get("s_key").asText();
            existingUser.setS_key(sKey);
            userService.deleteByID(existingUser.getId());
            // Update the user's additional details
            existingUser.setName(userDetails.getName());
            existingUser.setInterests(userDetails.getInterests());
            existingUser.setSkills(userDetails.getSkills());
            existingUser.setBestProjectDescription(userDetails.getBestProjectDescription());

            // Save the updated user
            userService.updateUser(existingUser);

            return new ResponseEntity<>("User details updated successfully", HttpStatus.OK);
        } catch (Throwable t) {
            // Handle other exceptions
            return new ResponseEntity<>("An error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/recruiterDetails")
    public ResponseEntity<String> updateRecruiterDetails(@RequestBody RecruiterDetails recruiterDetails) {
        try {
            // Check if the user exists based on the email
            User existingUser = userService.findByEmail(recruiterDetails.getEmail());

            if (existingUser == null) {
                return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
            }

            userService.deleteByID(existingUser.getId());

            // Update the user's additional details
            String s_key=rippleIntegration.makeAccountRequest();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(s_key);
            String sKey = jsonNode.get("s_key").asText();
            existingUser.setS_key(sKey);
            existingUser.setName(recruiterDetails.getName());
            existingUser.setCompany(recruiterDetails.getCompany());
            existingUser.setCompanyDescription(recruiterDetails.getCompanyDescription());

            // Save the updated user
            userService.updateUser(existingUser);

            return new ResponseEntity<>("User details updated successfully", HttpStatus.OK);
        } catch (Throwable t) {
            // Handle other exceptions
            return new ResponseEntity<>("An error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/userDetails")
    public ResponseEntity<User> getUserData(@RequestParam String email) {
        User user = userService.findByEmail(email);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
