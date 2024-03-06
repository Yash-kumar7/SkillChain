package com.backend.user;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "jobApplications")
public class JobApplication {
    private String id;
    private String userId; // User ID of the applicant
    private String jobId; // ID of the applied job
    private boolean paid; // Indicates if the payment is made
    private String status;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
