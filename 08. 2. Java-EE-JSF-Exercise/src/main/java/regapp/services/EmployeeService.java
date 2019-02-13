package regapp.services;

import regapp.domain.models.service.EmployeeServiceModel;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeService {
    boolean saveEmployee(EmployeeServiceModel employeeServiceModel);

    List<EmployeeServiceModel> getAllEmployees();

    void remove(String id);

    BigDecimal avgEmployeeSalary();

    BigDecimal totalEmployeeSalary();
}
