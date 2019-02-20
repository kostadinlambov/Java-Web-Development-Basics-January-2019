package sboj.services;

import sboj.domain.models.service.JobApplicationServiceModel;

import java.util.List;

public interface JobApplicationService {
    JobApplicationServiceModel createJobApplication(JobApplicationServiceModel jobApplicationServiceModel);

    JobApplicationServiceModel findJobApplicationById(String id);

    List<JobApplicationServiceModel> findAllJobApplications();

    void deleteJobApp(String id);
}
