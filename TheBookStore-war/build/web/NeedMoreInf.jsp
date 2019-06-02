<%-- 
    Document   : NeedMoreInf
    Created on : 2019-6-2, 11:06:50
    Author     : Hanliutong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>补全信息</title>
    </head>
    <body>
        <h1>补全信息</h1>
        <form action="CtrlServlet" method="post">
            <input type="hidden" name="page" value="NeedMoreInf">
            <input type="hidden" name="ISBN" value=<%=request.getParameter("ISBN")%>>
            ISBN:<%=request.getParameter("ISBN")%><br>
            书名：<input type="text" name="Title"required=""><br>
            作者：<input type="text" name="Author"required=""><br>
            数量：<input type="number" name="Stock"required="" min="1" value=<%=request.getParameter("Stock")%>><br>
            价格<input type="text" name="Price"required="" min="0" step="0.1"><br>
            <input type="submit" name = "submit" value="submit">
            <input type="submit" name = "submit" value="return">
        </form>
    </body>
</html>
