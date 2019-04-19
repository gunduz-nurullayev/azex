package az.test.web.login.forgot_password;

import az.test.domain.TokenHistory;
import az.test.domain.User;
import az.test.domain.enums.TokenType;
import az.test.repository.CommonDao;
import az.test.repository.UserDao;
import az.test.repository.implementation.CommonDaoImpl;
import az.test.repository.implementation.UserDaoImpl;
import az.test.repository.sifrelemeAndEmail.MailMessage;
import az.test.service.CommonService;
import az.test.service.UserService;
import az.test.service.implementation.CommonServiceImpl;
import az.test.service.implementation.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Optional;

@WebServlet(name = "ForgotPswdServlet", urlPatterns = "/forget-password")
public class ForgotPswdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset:UTF8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();

        String email = request.getParameter("email");

        UserDao userDao = new UserDaoImpl();
        UserService userService = new UserServiceImpl(userDao);

        CommonDao commonDao = new CommonDaoImpl();
        CommonService commonService = new CommonServiceImpl(commonDao);

        String token = userService.generateToken();
        Optional<User> optionalUser = userService.getUserDataByMail(email.toLowerCase());
        if (optionalUser.isPresent()) {
            long userId = optionalUser.get().getId();
            String name = optionalUser.get().getName();
            String surname = optionalUser.get().getSurname();


            TokenHistory tokenHistory = new TokenHistory(userId, token, TokenType.toValue(TokenType.RESEND), LocalDateTime.now(),LocalDateTime.now().plusHours(72));
            commonService.addTokenHistory(tokenHistory);

            String link = "http://localhost:8080/azex/reset-password?token=" + token;
            String text = " linkə klikləyərək şifrənizi yeniləyə bilərsiniz";

            MailMessage.mail(name, surname, link, email, text);
            while (session.getAttributeNames().hasMoreElements()) {
                String attrName = session.getAttributeNames().nextElement();
                session.removeAttribute(attrName);
            }

            request.setAttribute("succesMessage", "Mailinizə parol bərpası linki göndərildi");
            response.sendRedirect("forgot_password.jsp");


        } else {
            while (session.getAttributeNames().hasMoreElements()) {
                String attrName = session.getAttributeNames().nextElement();
                session.removeAttribute(attrName);
            }
            session.setAttribute("errorMessage", "Email yalnışdır");
            response.sendRedirect("forgot_password.jsp");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
