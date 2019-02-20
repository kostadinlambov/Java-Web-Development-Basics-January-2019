package org.softuni.exam.web.beans;

import org.softuni.exam.domain.models.service.JobApplicationServiceModel;
import org.softuni.exam.service.JobApplicationsService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named("jobApplicationDeleteBean")
@RequestScoped
public class JobApplicationDeleteBean extends BaseBean {
    private JobApplicationsService jobApplicationsService;

    public JobApplicationDeleteBean() {
    }

    @Inject
    public JobApplicationDeleteBean(JobApplicationsService jobApplicationsService) {
        this.jobApplicationsService = jobApplicationsService;
    }

    public JobApplicationServiceModel getJobApplication(String id) {
        JobApplicationServiceModel result = this.jobApplicationsService.getJobApplicationById(id);

        return result;
    }

    public void delete() {
        String id = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("id");
        this.jobApplicationsService.delete(id);
        this.redirect("/home");
    }
}
