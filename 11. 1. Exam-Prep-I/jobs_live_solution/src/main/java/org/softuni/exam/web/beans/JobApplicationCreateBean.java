package org.softuni.exam.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.models.binding.JobApplicationCreateBindingModel;
import org.softuni.exam.domain.models.service.JobApplicationServiceModel;
import org.softuni.exam.service.JobApplicationsService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("jobApplicationCreateBean")
@RequestScoped
public class JobApplicationCreateBean extends BaseBean {
    private JobApplicationCreateBindingModel jobApplicationModel;

    private JobApplicationsService jobApplicationsService;

    private ModelMapper modelMapper;

    public JobApplicationCreateBean() {
    }

    @Inject
    public JobApplicationCreateBean(JobApplicationsService jobApplicationsService, ModelMapper modelMapper) {
        this.jobApplicationsService = jobApplicationsService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.jobApplicationModel = new JobApplicationCreateBindingModel();
    }

    public JobApplicationCreateBindingModel getJobApplicationModel() {
        return this.jobApplicationModel;
    }

    public void setJobApplicationModel(JobApplicationCreateBindingModel jobApplicationModel) {
        this.jobApplicationModel = jobApplicationModel;
    }

    public void createJobApplication() {
        this.jobApplicationsService.createJobApplication(
                this.modelMapper.map(this.jobApplicationModel, JobApplicationServiceModel.class));

        this.redirect("/home");
    }
}
