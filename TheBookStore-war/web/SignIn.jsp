<%-- 
    Document   : SignIn
    Created on : 2019-6-27, 21:11:45
    Author     : Hanliutong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="md5.js"></script>  
<script>  
function check() {
    var P1 = document.forms["UserForm"]["PWD"].value;
    var P2 = document.forms["UserForm"]["PWD_re"].value;
    if (P1 != P2) {
        document.getElementById("info").innerHTML="<font color='red'>两次密码不相同</font>";
        document.getElementById("submit").disabled = true;
    }
    else{
        document.getElementById("info").innerHTML="";
        document.getElementById("submit").disabled = false;
    }
}
function validateForm(){  
    var P1 = document.forms["UserForm"]["PWD"].value;
    var hash=hex_md5(P1);  
    document.forms["UserForm"]["PWD"].value=hash;  
}  
function submit(){  
  //默认的提交处理，自定义的提交前处理方法不要用submit作为函数名  
}  
</script> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="mycss.css">
        <title>SignIn Page</title>
    </head>
    <body>
        <h1>用户注册</h1>
        <form name = "UserForm" action="CtrlServlet" method="post" onsubmit="validateForm()">
                <input type = "hidden" name="page" value="signin">
                用户名：    <input type = "text" name = "UserName" required=""><br>
                手机号：    <input type = "text" name = "phoneNum" pattern="[0-9]{11}" required=""><br>
                密码：      <input type = "password" name = "PWD" required=""><br>
                确认密码：  <input type = "password" name = "PWD_re" required="" onkeyup="check()"><span id="info"></span><br>
                <input type = "submit" name="submit" value="SignIN" id="submit">
        </form>
    </body>
</html>
