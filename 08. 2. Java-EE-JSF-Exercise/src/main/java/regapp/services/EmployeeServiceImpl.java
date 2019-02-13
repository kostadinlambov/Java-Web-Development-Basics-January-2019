package regapp.services;

import org.modelmapper.ModelMapper;
import regapp.domain.entities.Employee;
import regapp.domain.models.service.EmployeeServiceModel;
import regapp.repositories.EmployeeRepository;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Inject
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean saveEmployee(EmployeeServiceModel employeeServiceModel) {
        try {
            Employee employee = this.modelMapper.map(employeeServiceModel, Employee.class);
            this.employeeRepository.save(employee);
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    @Override
    public List<EmployeeServiceModel> getAllEmployees() {
        List<Employee> employeeList = this.employeeRepository.findAll();

        List<EmployeeServiceModel> employeeServiceModels = employeeList
                .stream()
                .map(employee -> this.modelMapper.map(employee, EmployeeServiceModel.class))
                .collect(Collectors.toList());

        return employeeServiceModels;
    }

    @Override
    public void remove(String id) {
        try {
            this.employeeRepository.remove(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Entity could not be deleted!");
        }
    }

    @Override
    public BigDecimal avgEmployeeSalary() {
        if (this.getAllEmployees().size() > 0) {
            try {
                return this.employeeRepository.getAvgEmployeeSalary();
            } catch (Exception e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        } else {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }
    }

    @Override
    public BigDecimal totalEmployeeSalary() {
        if (this.getAllEmployees().size() > 0) {
            try {
                return this.employeeRepository.getTotalEmployeeSalary();
            } catch (Exception e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        } else {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }
    }
}
