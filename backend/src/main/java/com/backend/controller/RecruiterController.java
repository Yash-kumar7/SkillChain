package com.backend.controller;
import com.backend.repository.JobApplicationRepository;
import com.backend.service.JobApplicationService;
import com.backend.service.JobPostingService;
import com.backend.service.UserService;
import com.backend.user.JobApplication;
import com.backend.user.JobPosting;
import com.backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recruiter")
public class RecruiterController {
    @Autowired
    private JobApplicationRepository jobApplicationRepository;
    @Autowired
    private JobPostingService jobPostingService;
    @Autowired
    private JobApplicationService jobApplicationService;
    @Autowired
    private UserService userService;

    @PostMapping("/postJob")
    public ResponseEntity<String> createJobPosting(@RequestBody JobPosting jobPosting) {
        String email = jobPosting.getEmailId();
        User user = userService.findByEmail(email);
        jobPosting.setS_key(user.getS_key());
        jobPosting.setPersonId(user.getId());
        jobPostingService.createJobPosting(jobPosting);
        return new ResponseEntity<>("Job posted successfully", HttpStatus.CREATED);
    }

    @GetMapping("/listJobs")
    public ResponseEntity<List<JobPosting>> getAllJobPostings() {
        List<JobPosting> jobPostings = jobPostingService.getAllJobPostings();
        return new ResponseEntity<>(jobPostings, HttpStatus.OK);
    }

    @GetMapping("/fetchJobById")
    public ResponseEntity<JobPosting> getJobPostingById(@RequestParam String jobId) {
        JobPosting jobPosting = jobPostingService.getJobPostingById(jobId);
        if (jobPosting == null) {
            new ResponseEntity<>("Job Not Found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(jobPosting, HttpStatus.OK);
    }

    @PutMapping("/updateJob/{jobId}")
    public ResponseEntity<String> updateJobPosting(@PathVariable String jobId, @RequestBody JobPosting jobPosting) {
        jobPostingService.updateJobPosting(jobId, jobPosting);
        return new ResponseEntity<>("Job posting updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deleteJob/{jobId}")
    public ResponseEntity<String> deleteJobPosting(@PathVariable String jobId) {
        jobPostingService.deleteJobPosting(jobId);
        return new ResponseEntity<>("Job posting deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/jobApplications/{jobPostingId}")
    public ResponseEntity<List<JobApplication>> viewJobApplications(@PathVariable String jobPostingId) {
        List<JobApplication> jobApplications = jobApplicationService.getJobApplicationsForJob(jobPostingId);
        return new ResponseEntity<>(jobApplications, HttpStatus.OK);
    }

    @PostMapping("/acceptJobApplication/{jobApplicationId}")
    public ResponseEntity<String> acceptJobApplication(@PathVariable String jobApplicationId) {
        jobApplicationService.updateJobApplicationStatus(jobApplicationId, "accepted");
        return new ResponseEntity<>("Job application accepted", HttpStatus.OK);
    }

    @PostMapping("/rejectJobApplication/{jobApplicationId}")
    public ResponseEntity<String> rejectJobApplication(@PathVariable String jobApplicationId) {
        jobApplicationService.updateJobApplicationStatus(jobApplicationId, "rejected");
        return new ResponseEntity<>("Job application rejected", HttpStatus.OK);
    }
}