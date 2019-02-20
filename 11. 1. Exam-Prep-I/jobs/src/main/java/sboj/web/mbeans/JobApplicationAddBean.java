package sboj.web.mbeans;

import org.modelmapper.ModelMapper;
import sboj.domain.models.binding.JobApplicationAddBindingModel;
import sboj.domain.models.service.JobApplicationServiceModel;
import sboj.services.JobApplicationService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class JobApplicationAddBean {

    private ModelMapper modelMapper;
    private JobApplicationService jobApplicationService;
    private JobApplicationAddBindingModel jobApplicationAddBindingModel;

    public JobApplicationAddBean() {
    }

    @Inject
    public JobApplicationAddBean(ModelMapper modelMapper, JobApplicationService jobApplicationService) {
        this.modelMapper = modelMapper;
        this.jobApplicationService = jobApplicationService;
    }

    @PostConstruct
    public void init() {
        this.jobApplicationAddBindingModel = new JobApplicationAddBindingModel();
    }

    public JobApplicationAddBindingModel getJobApplicationAddBindingModel() {
        return this.jobApplicationAddBindingModel;
    }

    public void setJobApplicationAddBindingModel(JobApplicationAddBindingModel jobApplicationAddBindingModel) {
        this.jobApplicationAddBindingModel = jobApplicationAddBindingModel;
    }

    public void create() throws IOException {
        JobApplicationServiceModel jobApplicationServiceModel = this.modelMapper.map(this.jobApplicationAddBindingModel, JobApplicationServiceModel.class);

        JobApplicationServiceModel jobApplication = this.jobApplicationService.createJobApplication(jobApplicationServiceModel);

        FacesContext.getCurrentInstance().getExternalContext().redirect("/jsf/home.xhtml");

    }
}
