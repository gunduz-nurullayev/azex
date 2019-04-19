package az.test.web.login.forgot_password;

import az.test.domain.User;
import az.test.repository.CustomerDao;
import az.test.repository.UserDao;
import az.test.repository.implementation.CustomerDaoImpl;
import az.test.repository.implementation.UserDaoImpl;
import az.test.repository.sifrelemeAndEmail.MailMessage;
import az.test.service.CustomerService;
import az.test.service.UserService;
import az.test.service.implementation.CustomerServiceImpl;
import az.test.service.implementation.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet(name = "ChangedPswdServlet", urlPatterns = "/pswd-changed")
public class ChangedPswdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String pswd1 = request.getParameter("pswd1");
        String pswd2 = request.getParameter("pswd2");

        String email = request.getParameter("email");

        System.out.println(email);
        UserDao userDao = new UserDaoImpl();
        UserService userService = new UserServiceImpl(userDao);

        Optional<User> optionalUser = userService.getUserDataByMail(email.toLowerCase());
        String name = optionalUser.get().getName();
        String surname = optionalUser.get().getSurname();

        if (pswd1.equals(pswd2)) {

            CustomerDao customerDao = new CustomerDaoImpl();
            CustomerService customerService = new CustomerServiceImpl(customerDao);

            int pswdCheck = customerService.passwordCheck(pswd1);
            if (pswdCheck == 1) {
                int status = customerService.updatePassword(pswd1, email);
                if (status == 1) {
                    String link = "http://localhost:8080/azex/";
                    String text = " sizin şifrəniz müvəfəqiyyətlə dəyişdirildi";
                    MailMessage.mail(name, surname, link, email, text);
                    response.sendRedirect("login.jsp");
                }
            } else {
                HttpSession session=request.getSession();
                session.setAttribute("pswdCheck","Parol en az 8 characterli olmalidir, en az 2 reqem ve 2  herf olmalidir!");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/customer/resetPassword.jsp");
                requestDispatcher.forward(request, response);
            }
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/customer/resetPassword.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
