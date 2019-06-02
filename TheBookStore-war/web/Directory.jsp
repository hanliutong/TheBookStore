<%-- 
    Document   : Directory
    Created on : 2019-5-21, 16:11:17
    Author     : Hanliutong
--%>

<%@page import="Entity.Booktable"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%String ans = ""; %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Directory Page</title>
    </head>       
    <body>
        <h1>Directory Page!</h1>
        <form action="CtrlServlet" method="post">
            <input type="hidden" name="page" value="Directory">
            <table border=\"1\">
                <tr><td>ISBN</td><td>书名</td><td>作者</td><td>库存</td><td>价格</td><td>数量</td></tr>
            <% List L = (List)request.getSession().getAttribute("booktable"); 
            Iterator It = L.iterator();
            Entity.Booktable bk = new Booktable();
            while(It.hasNext()){
                bk = (Entity.Booktable)It.next();
                %>
                <tr>
                    <td><%=bk.getIsbn()%> </td>
                    <td><%=bk.getTitle()%> </td>
                    <td><%=bk.getAuthor()%> </td>
                    <td><%=bk.getStock()%> </td>
                    <td><%=bk.getPrice()%> </td>
                    <td><input type="number" name = <%=bk.getIsbn()%> value="0" min="0" max=<%=bk.getStock()%>></td>
                </tr>
                <%}%>
            </table>        
            <input type="submit" name = "submit" value = "Cart">
            <input type="submit" name = "submit" value = "Search">
        </form>
    </body>
</html>
