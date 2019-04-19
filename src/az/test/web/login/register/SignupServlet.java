package az.test.web.login.register;

import az.test.domain.Customer;
import az.test.domain.TokenHistory;
import az.test.domain.User;
import az.test.domain.enums.TokenType;
import az.test.domain.enums.UserStatus;
import az.test.repository.CommonDao;
import az.test.repository.CustomerDao;
import az.test.repository.UserDao;
import az.test.repository.implementation.CommonDaoImpl;
import az.test.repository.implementation.CustomerDaoImpl;
import az.test.repository.implementation.UserDaoImpl;
import az.test.repository.sifrelemeAndEmail.MailMessage;
import az.test.repository.sifrelemeAndEmail.Sifreleme;
import az.test.service.CommonService;
import az.test.service.CustomerService;
import az.test.service.UserService;
import az.test.service.implementation.CommonServiceImpl;
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
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Optional;

@WebServlet(name = "SignupServlet", urlPatterns = "/signup")
public class SignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DateTimeException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("firstName");
        String surname = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password1 = request.getParameter("password");
        String password2 = request.getParameter("password_confirmation");
        if (password1.equals(password2)) {
            CustomerDao customerDao=new CustomerDaoImpl();
            CustomerService customerService=new CustomerServiceImpl(customerDao);
            int status=customerService.passwordCheck(password1);
             if(status==1){
                 String pswd = Sifreleme.hashPassword(password1);

                 HttpSession session = request.getSession();
                 UserDao userDao = new UserDaoImpl();
                 UserService userService = new UserServiceImpl(userDao);
                 Optional<User> mailCheck = userService.getUserDataByMail(email.toLowerCase());
                 if (mailCheck.isPresent()) {
                     while (session.getAttributeNames().hasMoreElements()) {
                         String attrName = session.getAttributeNames().nextElement();
                         session.removeAttribute(attrName);
                     }
                     session.setAttribute("message", "Artıq bu mail ilə qeydiyyatdan kecilmişdir!");
                     response.sendRedirect("login.jsp");
                 } else {
                     String token=userService.generateToken();
                     User user = new User(name, surname, email, pswd, 1, UserStatus.PENDING, LocalDateTime.now(), token, LocalDateTime.now().plusHours(72));
                     userService.addUser(user);

                     long userId=userService.getUserId(token);

                     TokenHistory tokenHistory=new TokenHistory(userId,token, TokenType.toValue(TokenType.ACTIVATION),LocalDateTime.now(),LocalDateTime.now().plusHours(72));

                     CommonDao commonDao=new CommonDaoImpl();
                     CommonService commonService=new CommonServiceImpl(commonDao);
                     commonService.addTokenHistory(tokenHistory);

                     while (session.getAttributeNames().hasMoreElements()) {
                         String attrName = session.getAttributeNames().nextElement();
                         session.removeAttribute(attrName);
                     }
                     session.setAttribute("message", "Zəhmət olmasa qeydiyyatınızı tamamlayın, təsdiq etmə linki  mailinizə göndərilib!");
                     //TODO link meselesinde sualim var
                     String link= "http://localhost:8080/azex/registrate?token="+token;
                     String text=" linkə daxil olaraq qeydiyyatınızı tamamlayın!";
                     MailMessage.mail(user.getName(), user.getSurname(),link, email, text);
                     response.sendRedirect("login.jsp");
                 }
             }
             else {
                 HttpSession session=request.getSession();
                 session.setAttribute("passwordCheck","Parol en az 8 characterli olmalidir, en az 2 reqem ve 2 herf olmalidir!");

                 response.sendRedirect("register.jsp");
             }

        } else {
            HttpSession session = request.getSession();
            while (session.getAttributeNames().hasMoreElements()) {
                String attrName = session.getAttributeNames().nextElement();
                session.removeAttribute(attrName);
            }
            session.setAttribute("message", "Parol tesdiqi yalnisdir, Zehmet olmasa yeniden qeydiyyatdan kecin");
            response.sendRedirect("register.jsp");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
