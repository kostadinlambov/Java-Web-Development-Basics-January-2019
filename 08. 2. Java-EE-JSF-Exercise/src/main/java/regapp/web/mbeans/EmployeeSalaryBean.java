package regapp.web.mbeans;

import regapp.services.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.math.BigDecimal;

@Named
@RequestScoped
public class EmployeeSalaryBean {
    private EmployeeService employeeService;
    private BigDecimal avgSalary;
    private BigDecimal totalMoney;

    public EmployeeSalaryBean() {
    }

    @Inject
    public EmployeeSalaryBean(EmployeeService employeeService) {
        this.employeeService = employeeService;
        this.totalMoney = this.employeeService.totalEmployeeSalary();
        this.avgSalary = this.employeeService.avgEmployeeSalary();
    }

    public void setAvgSalary(BigDecimal avgSalary) {
        this.avgSalary = avgSalary;
    }

    public BigDecimal getAvgSalary() throws IOException {
        return this.employeeService.avgEmployeeSalary();
    }

    public BigDecimal getTotalMoney() {
        return this.employeeService.totalEmployeeSalary();
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
}
