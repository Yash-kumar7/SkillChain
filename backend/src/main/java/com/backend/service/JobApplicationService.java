package com.backend.service;

import com.backend.user.JobApplication;

import java.util.List;

public interface JobApplicationService {
    void applyForJob(String jobId, String userId);
    boolean isApplicationPaid(String jobId);
    void markPaymentReceived(String jobId);
    void updateJobApplicationStatus(String jobApplicationId, String status);
    List<JobApplication> getJobApplicationsForJob(String jobId);
    List<JobApplication> getJobApplicationsByUserId(String userId);

}

