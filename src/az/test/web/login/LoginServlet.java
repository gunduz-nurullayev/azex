package az.test.web.login;

import az.test.domain.Balance;
import az.test.domain.Customer;
import az.test.domain.Role;
import az.test.domain.User;
import az.test.repository.CustomerDao;
import az.test.repository.UserDao;
import az.test.repository.implementation.CustomerDaoImpl;
import az.test.repository.implementation.UserDaoImpl;
import az.test.service.CustomerService;
import az.test.service.UserService;
import az.test.service.implementation.CustomerServiceImpl;
import az.test.service.implementation.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        UserDao userDao = new UserDaoImpl();
        UserService userService = new UserServiceImpl(userDao);
        

        if (request.getParameter("email") != null && request.getParameter("password") != null) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Optional<User> optionalUser = userService.authenticate(email, password);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                List<Role> roles = userService.getUserRoles(user.getId());
                user.setRoleList(roles);
                String page = roles.get(0).getDefaultController();

                Optional<User> userDatas=userService.getUserDataByMail(email);
                long userId=userDatas.get().getId();

                CustomerDao customerDao=new CustomerDaoImpl();
                CustomerService customerService=new CustomerServiceImpl(customerDao);

                Optional<Customer> optionalCustomer = customerService.getCustomerDataByUserId(userId);
                long customer_id=optionalCustomer.get().getId();

                Optional<Balance> optionalBalance=customerService.getBalanceDataByCustomerId(customer_id);
                BigDecimal balance=optionalBalance.get().getAmount();

                HttpSession session = request.getSession();
                session.setAttribute("loginTime", LocalDateTime.now());
                session.setAttribute("user", user);
                session.setAttribute("balance", balance);
                System.out.println("Page : "+page);
                response.sendRedirect(page);
            } else {
                HttpSession session = request.getSession();
                while (session.getAttributeNames().hasMoreElements()) {
                    String attrName = session.getAttributeNames().nextElement();
                    session.removeAttribute(attrName);
                }
                session.setAttribute("message", "Email ve ya sifre yanlisdir!");
                response.sendRedirect("login.jsp");
            }


        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
