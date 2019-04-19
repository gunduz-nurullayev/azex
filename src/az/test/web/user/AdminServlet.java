package az.test.web.user;

import az.test.domain.*;
import az.test.repository.CommonDao;
import az.test.repository.CustomerDao;
import az.test.repository.UserDao;
import az.test.repository.implementation.CommonDaoImpl;
import az.test.repository.implementation.CustomerDaoImpl;
import az.test.repository.implementation.UserDaoImpl;
import az.test.service.CommonService;
import az.test.service.CustomerService;
import az.test.service.UserService;
import az.test.service.implementation.CommonServiceImpl;
import az.test.service.implementation.CustomerServiceImpl;
import az.test.service.implementation.UserServiceImpl;
import az.test.web.common.WebConstants;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet(name = "AdminServlet", urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute(WebConstants.USER);
        CommonDao  commonDao=new CommonDaoImpl();
        CommonService commonService=new CommonServiceImpl(commonDao);

        CustomerDao customerDao=new CustomerDaoImpl();
        CustomerService customerService=new CustomerServiceImpl(customerDao);

        UserDao userDao=new UserDaoImpl();
        UserService userService=new UserServiceImpl(userDao);
        response.setContentType("text/json; charset=UTF-8");
        String action = "";

        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }

        if (action.equalsIgnoreCase("getFlexibleListItems")) {
            int start = Integer.parseInt(request.getParameter("start"));
            int length = Integer.parseInt(request.getParameter("length"));


            Map<String, FlexibleList> flexibleListMap = (Map<String, FlexibleList>) getServletContext().getAttribute("flexibleListMap");
            FlexibleList list = flexibleListMap.get("city");

            DataTablesResult result = new DataTablesResult();
            result.setDraw(Integer.parseInt(request.getParameter("draw")));
            result.setRecordsTotal(list.getItemList().size());
            result.setRecordsFiltered(list.getItemList().size());

            int startIndex = start;
            int endIndex = startIndex + length;


            if (startIndex < 0) {
                startIndex = 0;
            }

            if (endIndex > list.getItemList().size()) {
                endIndex = list.getItemList().size();
            }

            if (startIndex >= endIndex) {
                startIndex = endIndex;
            }

            List<FlexibleListItem> filteredList = list.getItemList().subList(startIndex, endIndex);


            String[][] data = new String[filteredList.size()][4];
            for (int i = 0; i < filteredList.size(); i++) {
                FlexibleListItem item = filteredList.get(i);
                data[i][0] = String.valueOf(item.getId());
                data[i][1] = String.valueOf(item.getValueId());
                data[i][2] = String.valueOf(item.getValueName());
                data[i][3] = String.valueOf(item.getInsertDate());
            }
            result.setData(data);

            Gson gson = new Gson();
            String json = gson.toJson(result);

            response.getWriter().print(json);
        }
        else if(action.equalsIgnoreCase("showCustomerList")){
            RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/admin/customer/index.jsp");
            dispatcher.forward(request,response);
        }
        else if(action.equalsIgnoreCase("getUserByCustomerId")){
               long customerId=Long.parseLong(request.getParameter("customerId"));

            Optional<CustomerDto> optionalUser= userDao.getUserDataByCustomerId(customerId);

            HttpSession session=request.getSession();
            session.setAttribute("customer", optionalUser.get());
            System.out.println("City:  "+ optionalUser.get().getCityName());
            session.setAttribute("city", commonService.getFlexibleListByName("city").get().getItemList());


            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/customer/view.jsp");
            dispatcher.forward(request, response);
        }
        else if(action.equalsIgnoreCase("showCustomer")){
           response.setContentType("application/json;charset=UTF-8");
           int start=Integer.parseInt(request.getParameter("start"));
           int length=Integer.parseInt(request.getParameter("length"));

           String query = request.getParameter("search[value]");
           List<CustomerDto> customerList=customerService.getCustomerList(start,length,query);
           //TODO tarix object kimi gorsenir cliente
           long customerCount=customerService.getCustomerCount(query);

           DataTablesResult1 dataTablesResult=new DataTablesResult1();
           dataTablesResult.setDraw(Integer.parseInt(request.getParameter("draw")));
           dataTablesResult.setRecordsTotal((int) customerCount);
           dataTablesResult.setRecordsFiltered((int) customerCount);
           dataTablesResult.setCustomerList(customerList);

            Gson gson = new Gson();
            String json = gson.toJson(dataTablesResult);
            response.getWriter().print(json);

        }
        else if(action.equalsIgnoreCase("updateCustomer")){
            long customerId=Long.parseLong(request.getParameter("customerId"));
            String name = request.getParameter("customerName");
            String surname = request.getParameter("customerSurname");
            List<String> customerList=new ArrayList<>();
           // customerList.add()
            int updateCustomer=customerService.updateCustomer(customerId);
        }
        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(user.getRoleList().get(0).getDefaultPage());
            dispatcher.forward(request, response);
        }
    }
}
