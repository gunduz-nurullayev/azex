package az.test.web.login.register;

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


@WebServlet(name = "RegistrationServlet", urlPatterns = "/registrate")
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          doProcces(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcces(request,response);
    }

    protected void doProcces(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        String tkn = request.getParameter("token");
        UserDao userDao = new UserDaoImpl();
        UserService userService = new UserServiceImpl(userDao);

        Optional<User> token = userService.checkToken(tkn);

        CommonDao commonDao = new CommonDaoImpl();
        CommonService commonService = new CommonServiceImpl(commonDao);

        InetAddress inetAddress = InetAddress.getLocalHost();
        String ip = inetAddress.getHostAddress();
        long userId = token.get().getId();
        ClickHistory clickHistory = new ClickHistory(tkn, userId, LocalDateTime.now(), ip);
        commonService.addClickHistory(clickHistory);

        if (token.isPresent()) {
            int compare = token.get().getTokenExpireDate().compareTo(LocalDateTime.now());
            if (compare > 0) {
                HttpSession session=request.getSession();
                session.setAttribute("channel", commonService.getFlexibleListByName("acquisition_channel").get().getItemList());
                session.setAttribute("city", commonService.getFlexibleListByName("city").get().getItemList());

                session.setAttribute("name", token.get());

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/customer/form.jsp");
                requestDispatcher.forward(request, response);
            } else {
                String newToken = userService.updateToken(token.get().getId());
                Optional<User> sendLink = userService.checkToken(newToken);

                TokenHistory tokenHistory = new TokenHistory(token.get().getId(), newToken, TokenType.toValue(TokenType.EXPIRE), LocalDateTime.now(), LocalDateTime.now().plusHours(72));
                commonService.addTokenHistory(tokenHistory);

                String link = "http://localhost:8080/azex/activate?token=" + newToken;
                String text = " linkə daxil olaraq qeydiyyatınızı tamamlayın!";
                MailMessage.mail(sendLink.get().getName(), sendLink.get().getSurname(), link, sendLink.get().getEmail(), text);
                HttpSession session = request.getSession();
                while (session.getAttributeNames().hasMoreElements()) {
                    String attrName = session.getAttributeNames().nextElement();
                    session.removeAttribute(attrName);
                }
                session.setAttribute("message", "Sizin mail təsdiqləmə müddətiniz bitmişdir, yeni təsdiqləmə mesajı mailinizə gondərilib, zəhmət olmasa təsdiq edin!");
                response.sendRedirect("login.jsp");
            }

        } else {
            System.out.println("Bu nomreli token movcud deyil!");
        }
    }
}