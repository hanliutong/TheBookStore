<%-- 
    Document   : NeedMoreInf
    Created on : 2019-6-2, 11:06:50
    Author     : Hanliutong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Entity.Booktable"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="mycss.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>图书信息</title>
    </head>
    <body>
        <%
            if(request.getSession().getAttribute("bookinfo") == null){
        %>
        <h1>补全信息</h1>
        <form action="CtrlServlet" method="post">
            <input type="hidden" name="page" value="NeedMoreInf">
            <input type="hidden" name="ISBN" value=<%=request.getParameter("ISBN")%>>
            ISBN:<%=request.getParameter("ISBN")%><br>
            书名：<input type="text" name="Title"required=""><br>
            作者：<input type="text" name="Author"required=""><br>
            数量：<input type="number" name="Stock"required="" min="1" value=<%=request.getParameter("Stock")%>><br>
            价格：<input type="text" name="Price"required="" min="0" step="0.1"><br>
            <input type="submit" name = "submit" value="submit">
        </form>
        <form action="CtrlServlet" method="post">
            <input type="hidden" name="page" value="NeedMoreInf">
            <input type="submit" name = "submit" value="return">
        </form>
        <% } 
        else{
               Booktable bk = (Booktable) request.getSession().getAttribute("bookinfo");
        %>
        <h1>图书信息</h1>
        <form action="CtrlServlet" method="post">
            <input type="hidden" name="page" value="NeedMoreInf">
            <input type="hidden" name="ISBN" value=<%=request.getParameter("ISBN")%>>
            ISBN:<%=request.getParameter("ISBN")%><br>
            书名：<%=bk.getTitle()%><br>
            作者：<%=bk.getAuthor()%><br>
            数量：<input type="number" name="Stock"required="" min="1" value=<%=request.getParameter("Stock")%>><br>
            价格：<%=bk.getPrice()%><br>
            <input type="submit" name = "submit" value="submit">
            <input type="submit" name = "submit" value="return">
        </form>   

       <%}%>    
    </body>
</html>
