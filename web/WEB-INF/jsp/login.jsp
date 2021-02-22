<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>登录页面 - 校园互助系统</title>
    <link rel="icon" type="image/png" sizes="16x16"
          href="${pageContext.request.contextPath }/images/origin/RPGTask.png">
    <meta http-equiv=Content-Type content="text/html; charset=utf-8">
    <link href="${pageContext.request.contextPath}/css/reset.css" type=text/css rel=stylesheet>
    <link href="${pageContext.request.contextPath}/css/pixel_style.css" type=text/css rel=stylesheet>
    <link href="${pageContext.request.contextPath}/css/pixel_login.css" type=text/css rel=stylesheet>
    <script src=${pageContext.request.contextPath}/js/jquery-1.11.3.min.js>
    </script>
    <script>
        $().ready(function () {
            $("#username").text(${username});
        });

        // 判断是登录账号和密码是否为空
        function check() {
            var username = $("#username").val();
            var password = $("#password").val();
            if (username === "" || password === "") {
                $("#message").text("账号或密码不能为空！");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<form class="login_box rpg_font center" action="${pageContext.request.contextPath}/login.action"
      method="post" onsubmit="return check()">
    <ul class="center">
        <h1 class="rpg_6h">欢迎访问校园互助系统</h1>
        <li class="line ">
            <label class="rpg_4h" for="username">用户名</label>
            <input class="rpg_4h rpg_font" type="text" id="username" name="username" placeholder="请输入用户名" required
                   autofocus>
        </li>
        <li>
            <label class="rpg_4h" for="password">密码</label>
            <input class="rpg_4h rpg_font" type="password" id="password" name="password" placeholder="请输入密码"
                   required>
        </li>
        <li>
            <button class="rpg_4h rpg_font" type="submit">登录</button>
            <button class="rpg_4h rpg_font" type="button"
                    onclick=location.href="${pageContext.request.contextPath}/register.action">注册
            </button>
        </li>
        <li>
            <span class="rpg_4h rpg_font" style="color: red;">${msg}</span>
        </li>
    </ul>
    <img style=" transform: translate(-180%, 30%);"
         src="${pageContext.request.contextPath }/images/pen.png">
</form>
</body>
</html>
