package sboj.web.mbeans;

import sboj.services.JobApplicationService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class JobDeleteBean {

    private JobApplicationService jobApplicationService;

    public JobDeleteBean() {
    }

    @Inject
    public JobApplicationService getJobApplicationService() {
        return this.jobApplicationService;
    }

    public void setJobApplicationService(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    public void delete(String id) throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String id1 = context.getRequestParameterMap().get("id");
//        String id = context.getRequestParameterMap().get("id");

        this.jobApplicationService.deleteJobApp(id);

        context.redirect("/jsf/home.xhtml");
    }
}
