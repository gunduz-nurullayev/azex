package az.test.web.filter;

import az.test.domain.User;
import az.test.web.common.WebConstants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthorizationFilter", urlPatterns = "/admin.jsp")
public class AuthorizationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        //System.out.println("");
        if (session.getAttribute(WebConstants.USER) != null||session.getAttribute(WebConstants.ROLE_MODERATOR)!=null) {
            System.out.println("user = "+session.getAttribute(WebConstants.USER));
            User user = (User) session.getAttribute(WebConstants.USER);
           /* long count = user.getRoleList().stream()
                    .filter(role -> role.getName().equalsIgnoreCase(WebConstants.ROLE_ADMIN))
                    .count();*/
           String roleName=user.getRoleList().get(0).getName();
            if (roleName.equalsIgnoreCase(WebConstants.ROLE_ADMIN)||roleName.equalsIgnoreCase(WebConstants.ROLE_MODERATOR)) {
                chain.doFilter(req, resp);
            } else {
                response.sendRedirect("login.jsp");
            }
        }
    }
    public void init(FilterConfig config) throws ServletException {

    }

}
