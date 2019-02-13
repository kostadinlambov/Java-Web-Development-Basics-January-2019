package regapp.web.mbeans;

import org.modelmapper.ModelMapper;
import regapp.domain.models.binding.EmployeeRegisterBindingModel;
import regapp.domain.models.service.EmployeeServiceModel;
import regapp.services.EmployeeService;
import regapp.util.ValidationUtil;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;


@Named
@RequestScoped
public class EmployeeRegisterBean {
    private EmployeeRegisterBindingModel employeeRegisterBindingModel;
    private EmployeeService employeeService;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;

    public EmployeeRegisterBean() {
        this.employeeRegisterBindingModel = new EmployeeRegisterBindingModel();
    }

    @Inject
    public EmployeeRegisterBean(EmployeeService employeeService, ModelMapper modelMapper, ValidationUtil validationUtil) {
       this();
       this.employeeService = employeeService;
       this.modelMapper = modelMapper;
       this.validationUtil = validationUtil;
    }

    public EmployeeRegisterBindingModel getEmployeeRegisterBindingModel() {
        return this.employeeRegisterBindingModel;
    }

    public void setEmployeeRegisterBindingModel(EmployeeRegisterBindingModel employeeRegisterBindingModel) {
        this.employeeRegisterBindingModel = employeeRegisterBindingModel;
    }


    public void register() throws IOException {
        System.out.println();
        if(this.validationUtil.isValid(this.employeeRegisterBindingModel)){
            EmployeeServiceModel serviceModel = this.modelMapper.map(this.employeeRegisterBindingModel, EmployeeServiceModel.class);
            this.employeeService.saveEmployee(serviceModel);
        }
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/");
    }
}


