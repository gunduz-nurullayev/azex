package az.test.repository.implementation;

import az.test.db.DbHelper;
import az.test.db.JdbcUtility;
import az.test.domain.Employee;
import az.test.domain.enums.Status;
import az.test.repository.EmployeeDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public List<Employee> getEmployeelist() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Employee> employeeList = new ArrayList<>();
        try {
            connection = DbHelper.dbConnection();
            ps = connection.prepareStatement(SqlQuery.GET_EMPLOYEE_LIST);

            rs = ps.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getLong("id"));
                employee.setName(rs.getString("name"));
                employee.setAge(rs.getLong("age"));
                employee.setAdress(rs.getString("address"));
                employee.setSalary(rs.getBigDecimal("salary"));
                employee.setStatus(Status.fromValue(rs.getInt("status")));
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(rs, ps, connection);
        }
        return employeeList;
    }

    @Override
    public Optional<Employee> getEmployeeById(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Optional<Employee> optionalEmployee = Optional.empty();
        try {
            connection = DbHelper.dbConnection();
            ps = connection.prepareStatement(SqlQuery.GET_EMPLOYEE_LIST_BY_ID);
            ps.setLong(1, id);
            rs=ps.executeQuery();
            if(rs.next()){
                Employee employee=new Employee();
                employee.setId(rs.getLong("id"));
                employee.setName(rs.getString("name"));
                employee.setAge(rs.getLong("age"));
                employee.setAdress(rs.getString("address"));
                employee.setSalary(rs.getBigDecimal("salary"));
                employee.setStatus(Status.fromValue(rs.getInt("status")));
                optionalEmployee=Optional.of(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return optionalEmployee;
    }

    @Override
    public void addEmployee(Employee employee) {
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            connection=DbHelper.dbConnection();
            ps=connection.prepareStatement(SqlQuery.ADD_EMPLOYEE);
            ps.setString(1,employee.getName());
            ps.setLong(2,employee.getAge());
            ps.setString(3,employee.getAdress());
            ps.setBigDecimal(4,employee.getSalary());
            ps.setInt(5,Status.toValue(Status.ACTIVE));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcUtility.close(rs,ps,connection);
        }
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
