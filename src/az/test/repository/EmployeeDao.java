package az.test.repository;

import az.test.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {
    List<Employee> getEmployeelist();
    Optional<Employee> getEmployeeById(long id);
    void addEmployee(Employee employee);
    List<Employee> updateEmployee(Employee employee);
    boolean deleteEmployee(Employee employee);
    //List<Employee> employeeSearch();
}
