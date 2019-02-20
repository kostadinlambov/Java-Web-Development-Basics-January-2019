package sboj.web.mbeans;

import org.modelmapper.ModelMapper;
import sboj.domain.models.service.JobApplicationServiceModel;
import sboj.domain.models.view.JobApplicationDetailsViewModel;
import sboj.services.JobApplicationService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class JobApplicationDetailsBean {

    private ModelMapper modelMapper;
    private JobApplicationService jobApplicationService;
    private JobApplicationDetailsViewModel jobApplicationDetailsViewModel;

    public JobApplicationDetailsBean() {
    }

    @Inject
    public JobApplicationDetailsBean(ModelMapper modelMapper, JobApplicationService jobApplicationService) {
        this.modelMapper = modelMapper;
        this.jobApplicationService = jobApplicationService;
    }

    @PostConstruct
    public void init(){
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String id = context.getRequestParameterMap().get("id");



        JobApplicationServiceModel jobApplicationById = this.jobApplicationService.findJobApplicationById(id);
        this.jobApplicationDetailsViewModel = this.modelMapper.map(jobApplicationById, JobApplicationDetailsViewModel.class);
    }

    public JobApplicationDetailsViewModel getJobApplicationDetailsViewModel() {
        return this.jobApplicationDetailsViewModel;
    }

    public void setJobApplicationDetailsViewModel(JobApplicationDetailsViewModel jobApplicationDetailsViewModel) {
        this.jobApplicationDetailsViewModel = jobApplicationDetailsViewModel;
    }

}
