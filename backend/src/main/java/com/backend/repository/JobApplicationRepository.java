package com.backend.repository;

import com.backend.user.JobApplication;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
public interface JobApplicationRepository extends MongoRepository<JobApplication, String> {
    List<JobApplication> findByUserId(String userId); // Find job applications by user ID
    List<JobApplication> findAllJobApplicationsByJobId(String jobId);
    JobApplication findByJobId(String jobId); // Find a job application by job ID
    // Additional custom query methods
    List<JobApplication> findAllByUserIdAndPaid(String userId, boolean paid); // Find job applications by user ID and payment status
    void deleteByUserIdAndJobId(String userId, String jobId); // Delete a job application by user ID and job ID
}
