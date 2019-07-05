<%-- 
    Document   : Directory
    Created on : 2019-5-21, 16:11:17
    Author     : Hanliutong
--%>

<%@page import="Entity.Booktable"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%String str =(String) request.getSession().getAttribute("UID");%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="mycss.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Directory Page</title>
    </head>       
    <body>
        <h1>Directory Page!</h1>
        <h2>用户<%=str%>，您好</h2>
        <form action="CtrlServlet" method="post">
            <input type="hidden" name="page" value="Directory">
            <%try{%>
            <table border=\"1\">
                <tr><td>ISBN</td><td>书名</td><td>作者</td><td>库存</td><td>价格</td><td>数量</td></tr>
            <% List L = (List)request.getSession().getAttribute("booktable"); 
            //异常最有可能从这里产生，当session超时后，会尝试对null调用getAttribute方法
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
            <% }//try
            catch(Exception e){%>
            <h3>服务器异常，请返回</h3>
            <a href="welcome.jsp">返回</a><br><br>
            <%
            }//catch
            %>       
            <input type="submit" name = "submit" value = "Cart">
            <input type="submit" name = "submit" value = "Search">
        </form>
    </body>
</html>
