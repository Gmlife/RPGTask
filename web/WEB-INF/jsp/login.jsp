<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String base = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + path;
%>
<!DOCTYPE HTML>
<html>
<head>
    <title>登录页面 - 校园互助系统</title>
    <link rel="icon" type="image/png" sizes="16x16"
          href="<%=base%>/images/RPGTask.png">
    <meta http-equiv=Content-Type content="text/html; charset=utf-8">
    <link href="<%=base%>/css/reset.css" type=text/css rel=stylesheet>
    <link href="<%=base%>/css/pixel_style.css" type=text/css rel=stylesheet>
    <link href="<%=base%>/css/pixel_login.css" type=text/css rel=stylesheet>
    <script src="<%=base%>/js/jquery-1.11.3.min.js"></script>
    <script src="<%=base%>/js/jquery.dataTables.min.js"></script>
    <script>
        $().ready(function () {
            $("#username").text("${username}");
        });
    </script>
</head>
<body>
<form class="login_box rpg_font center" action="<%=base%>/login.action"
      method="post">
    <ul class="center">
        <h1 class="rpg_6h">欢迎访问校园互助系统</h1>
        <li>
            <label class="rpg_3h" for="username">用户名</label>
            <input class="rpg_3h rpg_font" type="text" id="username" name="username" placeholder="请输入用户名" required
                   autofocus>
        </li>
        <li>
            <label class="rpg_3h" for="password">密码</label>
            <input class="rpg_3h rpg_font" type="password" id="password" name="password" placeholder="请输入密码"
                   required>
        </li>
        <li>
            <button class="rpg_4h rpg_font" type="submit">登录</button>
            <button class="rpg_4h rpg_font" type="button"
                    onclick=location.href="<%=base%>/register.action">注册
            </button>
        </li>
        <li>
            <span class="rpg_3h rpg_font red">${msg}</span>
        </li>
    </ul>
    <img style=" transform: translate(-180%, 30%);"
         src="<%=base%>/images/common_ui/pen.png">
</form>
</body>
</html>
