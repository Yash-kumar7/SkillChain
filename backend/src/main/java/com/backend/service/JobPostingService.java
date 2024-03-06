package com.backend.service;
import com.backend.user.JobPosting;

import java.util.List;

public interface JobPostingService {
    void createJobPosting(JobPosting jobPosting);
    List<JobPosting> getAllJobPostings();
    JobPosting getJobPostingById(String jobId);
    void updateJobPosting(String jobId, JobPosting updatedJobPosting);
    void deleteJobPosting(String jobId);
    List<JobPosting> getJobPostingsByLocation(String location);
}

