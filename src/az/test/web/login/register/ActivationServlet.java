package az.test.web.login.register;

import az.test.domain.Balance;
import az.test.domain.Customer;
import az.test.domain.User;
import az.test.repository.CustomerDao;
import az.test.repository.UserDao;
import az.test.repository.implementation.CommonDaoImpl;
import az.test.repository.implementation.CustomerDaoImpl;
import az.test.repository.implementation.UserDaoImpl;
import az.test.repository.sifrelemeAndEmail.WelcomeMessage;
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
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "ActivationServlet", urlPatterns = "/activate")
public class ActivationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcces(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcces(request, response);
    }

    protected void doProcces(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("html/text; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");

        String idCardPrefix = request.getParameter("id_card_prefix");
        String idCardNumber = request.getParameter("id_card_number");
        String pinCode = request.getParameter("pin_code");
        String cins = request.getParameter("gender");
        String number1 = request.getParameter("phone1");
        String number2 = request.getParameter("phone2");
        String address = request.getParameter("address");
        String seher = request.getParameter("city");
        String kuce = request.getParameter("district");
        String kanal = request.getParameter("channel");
        String referalCode = request.getParameter("referal_code");


        int error = 0;
        if ((year.isEmpty() || month.isEmpty() || day.isEmpty())) {
            error++;
            request.setAttribute("birthDateError", "Doğum tarixi boş olmamalıdır!");
        }
        if (idCardPrefix.isEmpty()) {
            error++;
            request.setAttribute("idCardPrefixError", "Ş/V seriyası seçilməlidir!");
        }
        if (idCardNumber.isEmpty()) {
            error++;
            request.setAttribute("idCardNumberError", "Ş/V seriya nömrəsi boş olmamalıdır!");
        }
        if (pinCode.isEmpty()) {
            error++;
            request.setAttribute("pinCodeError", "Pin kod boş olmamalıdır!");
        }
        if (cins.isEmpty()) {
            error++;
            request.setAttribute("cinsError", "Cins  seçilməlidir");
        }
        if (number1.isEmpty() && number2.isEmpty()) {
            error++;
            request.setAttribute("numberError", "Ən az bir nömrə daxil edilməlidir");
        }
        if (address.isEmpty()) {
            error++;
            request.setAttribute("addressError", "Ünvan daxil edilməlidir");
        }
        if (seher.isEmpty()) {
            error++;
            request.setAttribute("seherError", "Şəhər seçilməlidir  seçilməlidir");
        }
        if (error > 0) {
            request.setAttribute("month", month);
            request.setAttribute("year", year);
            request.setAttribute("day", day);

            request.setAttribute("idCardPrefix", idCardPrefix);
            request.setAttribute("idCardNumber", idCardNumber);
            request.setAttribute("pinCode", pinCode);
            request.setAttribute("cins", cins);
            request.setAttribute("number1", number1);
            request.setAttribute("number2", number2);
            request.setAttribute("address", address);
            request.setAttribute("seher", seher);
            request.setAttribute("kanal", kanal);
            request.setAttribute("kuce", kuce);
            request.setAttribute("referalCode", referalCode);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/customer/form.jsp");
            requestDispatcher.forward(request, response);
        } else {

            LocalDate birthDate = LocalDate.of(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day));

            String phone1 = request.getParameter("phone_prefix1") + number1;
            String phone2 = request.getParameter("phone_prefix2") + number2;

            int gender = Integer.parseInt(cins);
            long city = Long.parseLong(seher);
            long district = Long.parseLong(kuce);
            long channel = Long.parseLong(kanal);

            User user = new User(idCardPrefix, idCardNumber, pinCode, gender, phone1, phone2, birthDate, LocalDateTime.now());
            UserDao userDao = new UserDaoImpl();
            UserService userService = new UserServiceImpl(userDao);
            int userCheck = userService.addUserComplete(user, email.toLowerCase());

            Optional<User> optionalUser = userService.getUserDataByMail(email.toLowerCase());
            long userId = optionalUser.get().getId();
            String token = optionalUser.get().getToken();


            CustomerDao customerDao = new CustomerDaoImpl();
            CustomerService customerService = new CustomerServiceImpl(customerDao);
            String customerCode = customerService.getCustomerCode(name);


            Optional<Customer> optionalCustomer=customerService.getCustomerDataByUserId(userId);

            System.out.println("Optional customer: "+optionalCustomer);

            Customer customer = new Customer(customerCode, channel, referalCode, city, district, address);
            int customerCheck = customerService.addCustomer(customer, userId);
            System.out.println("Customer Check: "+ customerCheck);
            if (userCheck == 1 && customerCheck == 1) {
            /*List<Role> roleList=userService.getUserRoles(userId);
            String page=roleList.get(0).getDefaultPage();
            response.sendRedirect(page);*/
                HttpSession session = request.getSession();

                long customer_id=optionalCustomer.get().getId();

                Balance balance=new Balance(BigDecimal.ZERO, 1,customer_id,LocalDateTime.now());
                int status=customerService.createBalance(balance);
                if(status==1){
                   session.setAttribute("newBalance", balance.getAmount()+"AZN");
                WelcomeMessage.mail(name, surname, email);
                while (session.getAttributeNames().hasMoreElements()) {
                    String attrName = session.getAttributeNames().nextElement();
                    session.removeAttribute(attrName);
                }
                session.setAttribute("message", "Qeydiyyatınız tamamlanmışdır, mail və şifrənizi daxil edib, şəxsi kabinetinizə daxil ola bilərsiniz");
                response.sendRedirect("login.jsp");
            }else {
                    System.out.println("Balance yaradilmamisdir!");
                }
            }
        }
    }
}
