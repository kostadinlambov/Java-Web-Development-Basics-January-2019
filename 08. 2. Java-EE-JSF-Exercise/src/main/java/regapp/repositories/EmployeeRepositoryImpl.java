package regapp.repositories;

import regapp.domain.entities.Employee;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final EntityManager entityManager;

    @Inject
    public EmployeeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Employee save(Employee entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public List<Employee> findAll() {
        this.entityManager.getTransaction().begin();

        List<Employee> employeeList = this.entityManager
                .createQuery("SELECT e FROM Employee AS e", Employee.class)
                .getResultList();

        this.entityManager.getTransaction().commit();

        return employeeList;
    }

    @Override
    public Employee findById(String id) {
        this.entityManager.getTransaction().begin();

        Employee employee = this.entityManager
                .createQuery("SELECT e FROM Employee AS e WHERE e.id= :id", Employee.class)
                .setParameter("id", id)
                .getSingleResult();

        this.entityManager.getTransaction().commit();

        return employee;
    }


    public void remove(String id) {
        this.entityManager.getTransaction().begin();
        int count = this.entityManager
                .createQuery("DELETE FROM Employee AS e WHERE e.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        this.entityManager.getTransaction().commit();
    }

    @Override
    public BigDecimal getAvgEmployeeSalary() {

        this.entityManager.getTransaction().begin();
        Query query = this.entityManager
                .createQuery("SELECT AVG(e.salary) FROM Employee AS e");

        Object avgSalary =  query.getSingleResult();

        this.entityManager.getTransaction().commit();
        return (new BigDecimal(avgSalary.toString()).setScale(2, RoundingMode.HALF_UP)) ;
    }

    @Override
    public BigDecimal getTotalEmployeeSalary() {
            this.entityManager.getTransaction().begin();
            Query query = this.entityManager
                    .createQuery("SELECT SUM(e.salary) FROM Employee AS e");

            Object totalSalary = query.getSingleResult();

            this.entityManager.getTransaction().commit();
            return new BigDecimal(totalSalary.toString()).setScale(2, RoundingMode.HALF_UP);
    }
}
