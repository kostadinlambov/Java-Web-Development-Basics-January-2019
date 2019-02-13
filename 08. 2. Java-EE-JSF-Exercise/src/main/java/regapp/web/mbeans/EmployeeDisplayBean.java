package regapp.web.mbeans;

import org.modelmapper.ModelMapper;
import regapp.domain.models.view.EmployeeViewModel;
import regapp.services.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class EmployeeDisplayBean {

    private ModelMapper modelMapper;
    private EmployeeService employeeService;
    private List<EmployeeViewModel> employeeViewModelList;

    public EmployeeDisplayBean() {
    }

    @Inject
    public EmployeeDisplayBean(ModelMapper modelMapper, EmployeeService employeeService) {
        this.modelMapper = modelMapper;
        this.employeeService = employeeService;
        this.employeeViewModelList = this.employeeService
                .getAllEmployees()
                .stream()
                .map(employeeServiceModel -> this.modelMapper
                        .map(employeeServiceModel, EmployeeViewModel.class))
                .collect(Collectors.toList());
    }

    public List<EmployeeViewModel> getEmployeeViewModelList() {
        return this.employeeViewModelList;
    }

    public void setEmployeeViewModelList(List<EmployeeViewModel> employeeViewModelList) {
        this.employeeViewModelList = employeeViewModelList;
    }
}
