<%-- 
    Document   : welcome
    Created on : 2019-6-1, 18:24:21
    Author     : Hanliutong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%String str =(String) request.getSession().getAttribute("UID");%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="mycss.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>在线书城</h1>
        <h2>用户<%=str%>，您好</h2>
        <div>
            <form action="CtrlServlet" method="post">
                <input type="hidden" name="page" value="welcome">
                <input type = "submit" name="submit" value="Search">
                <input type = "submit" name="submit" value="Release">
            </form>
        <%--a href="Search.jsp">购书</a>
        <a href="Release.jsp">发布</a--%>
        
        </div>
    </body>
</html>
