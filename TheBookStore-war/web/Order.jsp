<%-- 
    Document   : Order
    Created on : 2019-5-21, 16:11:45
    Author     : Hanliutong
--%>

<%@page import="java.util.Iterator"%>
<%@page import="Entity.Booktable"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%String str =(String) request.getSession().getAttribute("UID");%>
<!DOCTYPE html>
<%  String addressStr = request.getParameter("address");
    addressStr = new String(addressStr.getBytes("ISO-8859-1"),"utf-8");%>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="mycss.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Page</title>
    </head>
    <body>
        <h1>Order Page</h1>
        <h2>用户<%=str%>，您好</h2>
        <form action="CtrlServlet" method="post">
            <input type="hidden" name="page" value="Order">
            <%try{%>

            <% 
            List L = (List)request.getSession().getAttribute("carttable"); 
            List<Integer> N = (List)request.getSession().getAttribute("numtable"); 
            request.getSession().removeAttribute("carttable");
            request.getSession().removeAttribute("booktable");
            request.getSession().removeAttribute("numtable");
            Iterator It = L.iterator();
            Entity.Booktable bk = new Booktable();
            double totalPeice = 0;
            int i = 0;%>
            <table border=\"1\">
                <tr><td>ISBN</td><td>书名</td><td>作者</td><td>库存</td><td>单价</td><td>数量</td><td>价格</td></tr>
            <% 
            while(It.hasNext()){
                bk = (Entity.Booktable)It.next();
                double price = bk.getPrice() *N.get(i);
                totalPeice+=price;
                %>
                <tr>
                    <td><%=bk.getIsbn()%> </td>
                    <td><%=bk.getTitle()%> </td>
                    <td><%=bk.getAuthor()%> </td>
                    <td><%=bk.getStock()%> </td>
                    <td><%=bk.getPrice()%> </td>
                    <td><%=N.get(i)%></td>
                    <td><%=price%></td>
                </tr>
                <% i++;
            }%>
            </table>
            <b>地址: <%=addressStr%></b><br>
            <b>总价: <%=totalPeice%></b><br>
           <% }//try
            catch(Exception e){%>
            <h3>下单失败</h3>
            <a href="welcome.jsp">返回</a>
            <%
            }//catch
            %>       
        </form>
    </body>
</html>
