package com.backend.service.impl;

import com.backend.repository.JobApplicationRepository;
import com.backend.service.JobApplicationService;
import com.backend.user.JobApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {
    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Override
    public void applyForJob(String jobId, String userId) {
        JobApplication jobApplication = new JobApplication();
        jobApplication.setJobId(jobId);
        jobApplication.setUserId(userId);
        jobApplication.setStatus("applied");
        jobApplication.setPaid(true);
        jobApplicationRepository.save(jobApplication);
    }

    @Override
    public boolean isApplicationPaid(String jobId) {
        JobApplication jobApplication = jobApplicationRepository.findByJobId(jobId);
        return jobApplication != null && jobApplication.isPaid();
    }

    @Override
    public void updateJobApplicationStatus(String jobApplicationId, String status) {
        JobApplication jobApplication = jobApplicationRepository.findById(jobApplicationId).orElse(null);
        if (jobApplication != null) {
            jobApplication.setStatus(status);
            jobApplicationRepository.save(jobApplication);
        }
    }

    @Override
    public void markPaymentReceived(String jobId) {
        JobApplication jobApplication = jobApplicationRepository.findByJobId(jobId);
        if (jobApplication != null) {
            jobApplication.setPaid(true); // Mark the payment as received
            jobApplicationRepository.save(jobApplication);
        }
    }

    @Override
    public List<JobApplication> getJobApplicationsByUserId(String userId) {
        return jobApplicationRepository.findByUserId(userId);
    }


    @Override
    public List<JobApplication> getJobApplicationsForJob(String jobId) {
        return jobApplicationRepository.findAllJobApplicationsByJobId(jobId);
    }


}
