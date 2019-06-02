<%-- 
    Document   : newjsp
    Created on : 2019-6-1, 18:27:40
    Author     : Hanliutong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Release</title>
    </head>
    <body>
        <h1>请输入图书信息</h1>
        <form method="post" action="CtrlServlet" >
            <input type="hidden" name="page" value="Release">
            ISBN:<input type="number" name="ISBN" required=""><br>
            <!--<input type="text" name="Title"required="">-->
            <!--<input type="text" name ="Author"required="">-->
            数量:<input type="number" name ="Stock"required="" min="1" value="1"><br>
            <!--<input type="number" name="Price"required="">-->
            <input type="submit" >
        </form>
    </body>
</html>
