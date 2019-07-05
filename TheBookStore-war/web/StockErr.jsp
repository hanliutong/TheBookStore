<%-- 
    Document   : StockErr
    Created on : 2019-6-2, 1:16:40
    Author     : Hanliutong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%String str =(String) request.getSession().getAttribute("UID");%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="mycss.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>库存变化</title>
    </head>
    <body>
        <h1>库存已经发生变化，请重新下单</h1>
        <h2>用户<%=str%>，您好</h2>
        <a href="Search.jsp">跳转</a>
    </body>
</html>
