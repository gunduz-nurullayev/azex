package az.test.service;

import az.test.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getEmployeeList();
    void addEmployee(Employee employee);
    Optional<Employee> getEmployeeById(long id);
    List<Employee> updateEmployee(Employee employee);
    boolean deleteEmployee(Employee employee);
    //List<Employee> employeeSearch();
}
