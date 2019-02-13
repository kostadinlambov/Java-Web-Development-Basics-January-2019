package regapp.repositories;

import regapp.domain.entities.Employee;

import java.math.BigDecimal;

public interface EmployeeRepository extends  GenericRepository<Employee, String>{

    BigDecimal getAvgEmployeeSalary();

    BigDecimal getTotalEmployeeSalary();
}
