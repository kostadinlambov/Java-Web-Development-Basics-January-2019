package regapp.web.mbeans;

import regapp.services.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class EmployeeRemoveBean {

    private EmployeeService employeeService;

    public EmployeeRemoveBean() {
    }

    @Inject
    public EmployeeRemoveBean(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void removeEmployee(String id) throws IOException {
        this.employeeService.remove(id);

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/");
    }
}
