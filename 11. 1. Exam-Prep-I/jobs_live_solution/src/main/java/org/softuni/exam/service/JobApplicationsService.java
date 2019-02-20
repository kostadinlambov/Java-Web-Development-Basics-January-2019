package org.softuni.exam.service;

import org.softuni.exam.domain.models.service.JobApplicationServiceModel;

import java.util.List;

public interface JobApplicationsService {
    JobApplicationServiceModel createJobApplication(JobApplicationServiceModel jobApplication);

    JobApplicationServiceModel getJobApplicationById(String id);

    List<JobApplicationServiceModel> getAllJobApplications();

    void delete(String id);
}
