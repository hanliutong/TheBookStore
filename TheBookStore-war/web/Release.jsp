<%-- 
    Document   : newjsp
    Created on : 2019-6-1, 18:27:40
    Author     : Hanliutong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%String str =(String) request.getSession().getAttribute("UID");%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="mycss.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Release</title>
    </head>
    <body>
        <h1>请输入图书信息</h1>
        <h2>用户<%=str%>，您好</h2>
        <form method="post" action="CtrlServlet" >
            <input type="hidden" name="page" value="Release">
            ISBN:<input type="number" name="ISBN" required=""><br>
            数量:<input type="number" name ="Stock"required="" min="1" value="1"><br>
            <input type="submit" >
        </form>
    </body>
</html>
