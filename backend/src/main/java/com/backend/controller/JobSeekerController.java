package com.backend.controller;

import com.backend.service.JobApplicationService;
import com.backend.service.JobPostingService;
import com.backend.service.UserService;
import com.backend.user.JobPosting;
import com.backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobSeeker")
public class JobSeekerController {
    @Autowired
    private JobApplicationService jobApplicationService;
    @Autowired
    private UserService userService;
    @Autowired
    private  RippleIntegration rippleIntegration;
    @Autowired
    private JobPostingService jobPostingService;

    @PostMapping("/apply/{jobId}")
    public ResponseEntity<String> applyForJob(@PathVariable String jobId, @RequestBody User applicant) {
        JobPosting jobPosting = jobPostingService.getJobPostingById(jobId);

        if (jobPosting == null) {
            return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
        }
        // Get the associated s_key from the job posting
        String companySeed = jobPosting.getS_key();
        // Continue with the rest of your logic
        User user = userService.findByEmail(applicant.getEmail());
        String userSeed = user.getS_key();
        String email = applicant.getEmail();
        String userId = user.getId(); // Assuming the User object has an "id" field
        rippleIntegration.jobInterestRequest(userSeed,companySeed);
        jobApplicationService.applyForJob(jobId, userId);

        return new ResponseEntity<>("Job application submitted successfully", HttpStatus.CREATED);
    }
}

