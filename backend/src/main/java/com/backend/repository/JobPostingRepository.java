package com.backend.repository;

import com.backend.user.JobPosting;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface JobPostingRepository extends MongoRepository<JobPosting, String> {
    List<JobPosting> findAll(); // Find all job postings

    Optional<JobPosting> findById(String id); // Find a job posting by ID

    void deleteById(String id); // Delete a job posting by ID

    List<JobPosting> findByLocation(String location);
}

