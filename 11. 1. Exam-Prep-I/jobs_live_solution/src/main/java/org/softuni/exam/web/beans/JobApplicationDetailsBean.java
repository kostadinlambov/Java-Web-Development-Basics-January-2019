package org.softuni.exam.web.beans;

import org.softuni.exam.domain.models.service.JobApplicationServiceModel;
import org.softuni.exam.service.JobApplicationsService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("jobApplicationDetailsBean")
@RequestScoped
public class JobApplicationDetailsBean extends BaseBean {
    private JobApplicationsService jobApplicationsService;

    public JobApplicationDetailsBean() {
    }

    @Inject
    public JobApplicationDetailsBean(JobApplicationsService jobApplicationsService) {
        this.jobApplicationsService = jobApplicationsService;
    }

    public JobApplicationServiceModel getJobApplication(String id) {
        return this.jobApplicationsService.getJobApplicationById(id);
    }
}
