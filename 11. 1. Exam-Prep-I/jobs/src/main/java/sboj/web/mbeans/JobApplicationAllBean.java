package sboj.web.mbeans;

import org.modelmapper.ModelMapper;
import sboj.domain.models.view.JobApplicationAllViewModel;
import sboj.services.JobApplicationService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class JobApplicationAllBean {
    private ModelMapper modelMapper;
    private JobApplicationService jobApplicationService;
    private List<JobApplicationAllViewModel> jobApplications;

    public JobApplicationAllBean() {
    }

    @Inject
    public JobApplicationAllBean(ModelMapper modelMapper, JobApplicationService jobApplicationService) {
        this.modelMapper = modelMapper;
        this.jobApplicationService = jobApplicationService;

    }

    @PostConstruct
    public void init(){
        this.jobApplications = this.jobApplicationService.findAllJobApplications()
                .stream()
                .map(job -> this.modelMapper
                        .map(job, JobApplicationAllViewModel.class))
                .collect(Collectors.toList());
    }

    public List<JobApplicationAllViewModel> getJobApplicationAllViewModelList() {
        return this.jobApplications;
    }

    public void setJobApplicationAllViewModelList(List<JobApplicationAllViewModel> jobApplications) {
        this.jobApplications = jobApplications;
    }
}
