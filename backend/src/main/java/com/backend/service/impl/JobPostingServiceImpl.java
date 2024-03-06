package com.backend.service.impl;

import com.backend.repository.JobPostingRepository;
import com.backend.service.JobPostingService;
import com.backend.user.JobPosting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPostingServiceImpl implements JobPostingService {


    @Autowired
    private JobPostingRepository jobPostingRepository;

    @Override
    public void createJobPosting(JobPosting jobPosting) {
        jobPostingRepository.save(jobPosting);
    }

    @Override
    public JobPosting getJobPostingById(String jobId) {
        return jobPostingRepository.findById(jobId).orElse(null);
    }

    @Override
    public List<JobPosting> getAllJobPostings() {
        return jobPostingRepository.findAll();
    }

    @Override
    public void updateJobPosting(String jobId, JobPosting updatedJobPosting) {
        JobPosting existingJobPosting = jobPostingRepository.findById(jobId).orElse(null);
        if (existingJobPosting != null) {
            // Update the fields of the existing job posting
            existingJobPosting.setTitle(updatedJobPosting.getTitle());
            existingJobPosting.setDescription(updatedJobPosting.getDescription());
            existingJobPosting.setSalary(updatedJobPosting.getSalary());
            existingJobPosting.setLocation(updatedJobPosting.getLocation());
            // Save the updated job posting
            jobPostingRepository.save(existingJobPosting);
        }
    }

    @Override
    public void deleteJobPosting(String jobId) {
        jobPostingRepository.deleteById(jobId);
    }

    @Override
    public List<JobPosting> getJobPostingsByLocation(String location) {
        return jobPostingRepository.findByLocation(location);
    }

}
