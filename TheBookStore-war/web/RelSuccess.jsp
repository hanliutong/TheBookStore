<%-- 
    Document   : RelSuccess
    Created on : 2019-6-2, 11:05:29
    Author     : Hanliutong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%String str =(String) request.getSession().getAttribute("UID");%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="mycss.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>发布成功</title>
    </head>
    <body>
        <h1>发布成功</h1>
        <h2>用户<%=str%>，您好</h2>
        <%
            request.getSession().removeAttribute("bookinfo");
        %>
        <div>
        <a href="welcome.jsp"> 返回 </a>
        </div>
    </body>
</html>
