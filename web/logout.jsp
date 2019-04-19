<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 12.02.2019
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
    <title>Logout</title>
    <script language="javascript">
        window.history.forward(1);
        browser.cache.offline.enable = false;
        function noBack() { window.history.forward(1);}

        self.close()
        function disableBackButton()
        {
        window.history.forward(1); 
        }
        setTimeout("disableBackButton()", 0);
    </script>
</head>
<body onload="noBack()">
       <%
           request.getSession(false).invalidate();
           //TODO LOGOUT DA KESIN TEMIZLENMESI NECE OLUR
           response.setHeader("Pragma", "no-cache"); //HTTP 1.0
           response.setHeader("Cache-Control", "no-cache");
           response.setDateHeader("Expires", 0); //prevents caching at the proxy server
           System.out.println("Sessiya oldu login seyfesine redirect olundu!");
           response.sendRedirect("login.jsp");
       %>
</body>
</html>
