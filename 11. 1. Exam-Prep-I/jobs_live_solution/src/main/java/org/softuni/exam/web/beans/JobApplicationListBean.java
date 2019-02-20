package org.softuni.exam.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.models.binding.JobApplicationCreateBindingModel;
import org.softuni.exam.domain.models.service.JobApplicationServiceModel;
import org.softuni.exam.service.JobApplicationsService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named("jobApplicationListBean")
@RequestScoped
public class JobApplicationListBean extends BaseBean {
    private JobApplicationsService jobApplicationsService;

    private List<JobApplicationServiceModel> jobApplications;

    public JobApplicationListBean() {
    }

    @Inject
    public JobApplicationListBean(JobApplicationsService jobApplicationsService) {
        this.jobApplicationsService = jobApplicationsService;
    }

    @PostConstruct
    public void init() {
        this.setJobApplications(this.jobApplicationsService.getAllJobApplications());
        this.getJobApplications().forEach(x -> x.setSector(x.getSector().toLowerCase()));
    }

    public List<JobApplicationServiceModel> getJobApplications() {
        return this.jobApplications;
    }

    public void setJobApplications(List<JobApplicationServiceModel> jobApplications) {
        this.jobApplications = jobApplications;
    }
}
