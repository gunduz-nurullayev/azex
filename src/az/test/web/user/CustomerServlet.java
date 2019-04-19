package az.test.web.user;

import az.test.crawler.Crawler;
import az.test.crawler.TrendYolCrawler;
import az.test.domain.Product;
import az.test.domain.User;
import az.test.domain.enums.CrawlerType;
import az.test.web.common.WebConstants;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute(WebConstants.USER);
        response.setContentType("text/html; charset=UTF-8");

        String action = "";
         /*
         1.view addresses
         2.view balance
         3.export balance to pdf
         4.view profile
         5.update/save profile
         6.change password
         7.view orders
         8.view order detail
         9.send order link
         10.remove order link
         11.update order link
         12.view declaration
         13.download declaration file
         14.add declaration
         15. view last 30 days orders
         16. view message list
         17.view message
         18.delete message
         19.reply to message
         20.send new message
         21.logout
          */
        // System.out.println("act: "+request.getParameter("action"));
        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }
        System.out.println("action: " + action);
        if (action.equalsIgnoreCase(WebConstants.ACTION_CUSTOMER_VIEW_ADDRESSES)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/customer/addresses.jsp");
            dispatcher.forward(request, response);
        } else if (action.equalsIgnoreCase(WebConstants.VIEW_ORDERS)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/customer/new_order.jsp");
            dispatcher.forward(request, response);
        } else if (action.equalsIgnoreCase(WebConstants.VIEW_BALANCE)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/customer/balance.jsp");
            dispatcher.forward(request, response);
        } else if (action.equalsIgnoreCase(WebConstants.VIEW_MESSAGES)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/customer/message.jsp");
            dispatcher.forward(request, response);
        } else if (action.equalsIgnoreCase(WebConstants.VIEW_DECLARATION)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/customer/declaration.jsp");
            dispatcher.forward(request, response);
        } else if (action.equalsIgnoreCase(WebConstants.VIEW_PROFILE)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/customer/profile.jsp");
            dispatcher.forward(request, response);
        } else if (action.equalsIgnoreCase(WebConstants.VIEW_CHANGE_PASSWORD)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/customer/change_password.jsp");
            dispatcher.forward(request, response);
        } else if (action.equalsIgnoreCase("order")) {
            Crawler crawler = CrawlerType.getTypeByNum(2).getCrawler();
            String link = request.getParameter("orderLink");
            Optional<Product> optionalProduct = crawler.parse(link);
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                System.out.println(product);
                Gson gson = new Gson();
                String json = gson.toJson(product);              
                response.getWriter().print(json);
            } else {
                System.out.println("Crawler tapilmadi!");
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(user.getRoleList().get(0).getDefaultPage());
            dispatcher.forward(request, response);
        }
    }
}
