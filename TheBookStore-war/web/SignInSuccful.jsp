<%-- 
    Document   : SignInSuccful
    Created on : 2019-6-27, 22:43:09
    Author     : Hanliutong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%String NewUID =(String) request.getSession().getAttribute("newUserId");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="mycss.css">
        <title>Successful Page</title>
    </head>
    <body>
        <h1>注册成功!</h1>
        <h1>请牢记UID：<%=NewUID%>,用于<a href="Login.jsp">登录</a>。</h1>
    </body>
</html>
