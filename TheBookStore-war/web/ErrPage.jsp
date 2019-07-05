<%-- 
    Document   : ErrPage
    Created on : 2019-6-27, 16:56:52
    Author     : Hanliutong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%String UID =(String) request.getSession().getAttribute("UID");%>
<%String point =(String) request.getSession().getAttribute("point");%>
<%String totalPrice =(String) request.getSession().getAttribute("totalPrice");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <link rel="stylesheet" type="text/css" href="mycss.css">
        <title>Error Page</title>
    </head>
    <body>
        <h1>余额不足</h1>
        <div>
        用户：<%=UID%>您好，<br>
        订单总价：<%=totalPrice%><br>
        当前余额：<%=point%><br>
        </div>
        <a href="Cart.jsp">返回购物车</a><br>
        <a href="Release.jsp">发布</a>
    </body>
</html>
