package az.test.web.old;

import az.test.domain.Employee;
import az.test.repository.EmployeeDao;
import az.test.repository.implementation.EmployeeDaoImpl;
import az.test.service.EmployeeService;
import az.test.service.implementation.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "DataBaseServlet", urlPatterns = "/db")
public class DataBaseServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            EmployeeDao employeeDao = new EmployeeDaoImpl();
            EmployeeService employeeService = new EmployeeServiceImpl(employeeDao);
            List<Employee> employeeList = new ArrayList<>();
            if (request.getParameter("id") != null) {
                Long id=Long.parseLong(request.getParameter("id"));
                Optional<Employee> optionalEmployee =employeeService.getEmployeeById(1);
                if(optionalEmployee.isPresent()){
                    employeeList.add(optionalEmployee.get());
                }
            } else {
                employeeList = employeeService.getEmployeeList();
            }
           // employeeList.forEach(out::println);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Employee</title>");
            out.println("</head>");
            out.println("<body><table border='1'><tr><th> Id </th><th> Name </th><th> Age </th> <th> Addres </th> <th> Salary </th></tr>" );
            for (int i = 0; i < employeeList.size(); i++) {
                out.println("<tr><td>"+employeeList.get(i).getId()+"</td>");
                out.println("<td>"+employeeList.get(i).getName()+"</td>");
                out.println("<td>"+employeeList.get(i).getAge()+"</td>");
                out.println("<td>"+employeeList.get(i).getAdress()+"</td>");
                out.println("<td>"+employeeList.get(i).getSalary()+"</td><td><a href='login1.jsp'>update</a></td><td><a href='login1.jsp'>delete</a></td></tr>");
                
            }
            
            out.println("</table></body></html>");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
