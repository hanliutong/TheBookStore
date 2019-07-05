<%-- 
    Document   : Cart
    Created on : 2019-5-21, 16:11:33
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
        <title>Cart Page</title>
    </head>
    <body>
        <h1>Cart Page</h1>
        <h2>用户<%=str%>，您好</h2>
        <form action="CtrlServlet" method="post">
            <input type="hidden" name="page" value="Cart">

            <%List L = (List)request.getSession().getAttribute("carttable"); 
            if(L.size() == 0){%>
            <b>购物车为空</b><br>
            <%}
            else{ %>
            <%try{%>
                <table border=\"1\">
                    <tr><td>ISBN</td><td>书名</td><td>作者</td><td>库存</td><td>单价</td><td>数量</td></tr>
                <% 
                List N = (List)request.getSession().getAttribute("numtable"); 
                Iterator It = L.iterator();
                Entity.Booktable bk = new Booktable();
                int i = 0;
                while(It.hasNext()){
                    bk = (Entity.Booktable)It.next();
                    %>
                    <tr>
                        <td><%=bk.getIsbn()%> </td>
                        <td><%=bk.getTitle()%> </td>
                        <td><%=bk.getAuthor()%> </td>
                        <td><%=bk.getStock()%> </td>
                        <td><%=bk.getPrice()%> </td>
                        <td><input type="number" name = <%=bk.getIsbn()%> value=<%=N.get(i)%> min="0" max=<%=bk.getStock()%>></td>
                    </tr>
                    <% i++;
            }//while%>
            </table>    
            <input type="text" name ="address" value="请输入地址，默认自提">
            <br>
            <input type="submit" name = "submit" value = "Order">
                       <% 
            }//try
            catch(Exception e){%>
            <h3>购物车异常，请返回</h3>
            <a href="welcome.jsp">返回</a>
            <%
            }//catch
            %>  
            <%}//else%>
            <input type="submit" name = "submit" value = "Directory">
            <input type="submit" name = "submit" value = "Search">
  
            
        </form>

    </body>
</html>
