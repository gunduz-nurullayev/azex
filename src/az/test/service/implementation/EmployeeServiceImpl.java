package az.test.service.implementation;

import az.test.domain.Employee;
import az.test.repository.EmployeeDao;
import az.test.repository.implementation.EmployeeDaoImpl;
import az.test.service.EmployeeService;

import java.util.List;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> getEmployeeList() {
        return employeeDao.getEmployeelist();
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
    }

    @Override
    public Optional<Employee> getEmployeeById(long id) {
        return employeeDao.getEmployeeById(id);
    }

    @Override
    public List<Employee> updateEmployee(Employee employee) {
        return null;
    }

    @Override
    public boolean deleteEmployee(Employee employee) {
        return false;
    }
}
