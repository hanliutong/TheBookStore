<%-- 
    Document   : Login
    Created on : 2019-6-27, 13:23:05
    Author     : Hanliutong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript" src="md5.js"></script>  
<script>  
function validateForm() {
    var x = document.forms["UserForm"]["PWD"].value;
    if (x != null) {
        var hash=hex_md5(x);  
        document.forms["UserForm"]["PWD"].value=hash;  
    }
}
function submit(){  
  //默认的提交处理，自定义的提交前处理方法不要用submit作为函数名  
}  
</script> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="mycss.css">
        <title>Login Page</title>
    </head>
    <body>
        <h1>请登录</h1>
        <form name = "UserForm" onsubmit="return validateForm()" action="CtrlServlet" method="post" >
            <input type="hidden" name="page" value="Login">
            账号：<input type="text" name="USR"required=""><br>
            密码：<input type="password" name="PWD"required=""><br>
            <input type="submit" name = "submit" value="登陆">
        </form>
        <div>没有账号？<a href="SignIn.jsp">点此注册</a></div>
    </body>
</html>
