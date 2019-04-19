<%@ page import="az.test.domain.FlexibleList" %>
<%@ page import="java.util.Map" %>
<%@ page import="az.test.domain.FlexibleListItem" %><%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 07.02.2019
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    ${sessionScope.user.email}, xos gelmisiniz! <br/>
     Giris vaxtiniz: ${sessionScope.loginTime}

    <%
        Map<String, FlexibleList> flexibleListMap = (Map<String, FlexibleList>) request.getServletContext().getAttribute("flexibleListMap");

        FlexibleList list = flexibleListMap.get("acquisition_channel");
        //out.println("Map = "+flexibleListMap);
        out.println("<h2>" + list.getName() + "</h2>");
        for(FlexibleListItem item : list.getItemList()) {
            out.println("<p>" + item.getValueId() + ", " + item.getValueName() + ", " + item.getInsertDate() + "</p>");
        }
%>
</body>
</html>