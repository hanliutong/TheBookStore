<%-- 
    Document   : Search
    Created on : 2019-5-21, 16:09:04
    Author     : Hanliutong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="mycss.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <h1>请输入图书名称</h1>
        <form action="CtrlServlet" method="post">
            <input type="hidden" name="page" value="Search">
            <input type ="text" name="bookname" required="">
            <input type="submit" value="查询">
        </form>
    </body>
</html>
