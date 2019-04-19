package az.test.web.login.forgot_password;

import az.test.domain.ClickHistory;
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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.Optional;

@WebServlet(name = "ResetPswdServlet", urlPatterns = "/reset-password")
public class ResetPswdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String token = request.getParameter("token");


        CommonDao commonDao = new CommonDaoImpl();
        CommonService commonService = new CommonServiceImpl(commonDao);

        Optional<TokenHistory> optionalTokenHistory = commonService.getTokenHistoryByToken(token);
        long userId = optionalTokenHistory.get().getUser_id();
        LocalDateTime tokenExpireDate = optionalTokenHistory.get().getTokenExpireDate();

        InetAddress inetAddress = InetAddress.getLocalHost();
        String ip = inetAddress.getHostAddress();

        ClickHistory clickHistory = new ClickHistory(token, userId, LocalDateTime.now(), ip);
        commonService.addClickHistory(clickHistory);

        UserDao userDao = new UserDaoImpl();
        UserService userService = new UserServiceImpl(userDao);

        Optional<User> optionalUser = userService.getUserDataById(userId);
        String name = optionalUser.get().getName();
        String surname = optionalUser.get().getSurname();
        String email = optionalUser.get().getEmail();

        if (optionalTokenHistory.isPresent()) {
            if (tokenExpireDate.compareTo(LocalDateTime.now()) > 0) {
                HttpSession session = request.getSession();
                session.setAttribute("mail", email);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/customer/resetPassword.jsp");
                requestDispatcher.forward(request, response);
            } else {

                String newToken = userService.generateToken();
                TokenHistory tokenHistory = new TokenHistory(userId, newToken, TokenType.toValue(TokenType.EXPIRE), LocalDateTime.now(), LocalDateTime.now().plusHours(72));
                commonService.addTokenHistory(tokenHistory);


                String link = "http://localhost:8080/azex/activate?token=" + newToken;
                String text = " linkə daxil olaraq şifrənizi dəyişə bilərsiniz!";
                MailMessage.mail(name, surname, link, email, text);
                HttpSession session = request.getSession();
                while (session.getAttributeNames().hasMoreElements()) {
                    String attrName = session.getAttributeNames().nextElement();
                    session.removeAttribute(attrName);
                }
                session.setAttribute("message", "Sizin mail təsdiqləmə müddətiniz bitmişdir, yeni təsdiqləmə mesajı mailinizə gondərilib, zəhmət olmasa təsdiq edin!");
                response.sendRedirect("login.jsp");
            }

        } else {
            response.sendRedirect("login.jsp");
        }


    }
}
