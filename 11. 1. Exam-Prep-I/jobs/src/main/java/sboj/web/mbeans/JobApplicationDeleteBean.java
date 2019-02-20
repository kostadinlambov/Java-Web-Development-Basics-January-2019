package sboj.web.mbeans;

import org.modelmapper.ModelMapper;
import sboj.domain.models.service.JobApplicationServiceModel;
import sboj.domain.models.view.JobApplicationDeleteViewModel;
import sboj.services.JobApplicationService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class JobApplicationDeleteBean {
    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;
    private JobApplicationDeleteViewModel jobApplicationDeleteViewModel;

    public JobApplicationDeleteBean() {
    }

    @Inject
    public JobApplicationDeleteBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
//        this.init();
    }

    @PostConstruct
    private void init(){
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String id = context.getRequestParameterMap().get("id");

        HttpSession session = (HttpSession) context.getSession(false);

        if(id != null){
            session.setAttribute("jobAppId", id);
            JobApplicationServiceModel jobApplicationById = this.jobApplicationService.findJobApplicationById(id);
            this.jobApplicationDeleteViewModel = this.modelMapper
                    .map(jobApplicationById, JobApplicationDeleteViewModel.class);
        }

    }

    public JobApplicationDeleteViewModel getJobApplicationDeleteViewModel() {
        return this.jobApplicationDeleteViewModel;
    }

    public void setJobApplicationDeleteViewModel(JobApplicationDeleteViewModel jobApplicationDeleteViewModel) {
        this.jobApplicationDeleteViewModel = jobApplicationDeleteViewModel;
    }

    public void delete() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) context.getSession(false);
        String jobAppId = (String)session.getAttribute("jobAppId");
//        String id = context.getRequestParameterMap().get("id");

        this.jobApplicationService.deleteJobApp(jobAppId);

        session.setAttribute("jobAppId", null);

        context.redirect("/jsf/home.xhtml");
    }
}
