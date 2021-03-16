<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>注册界面 - 校园互助系统</title>
    <link rel="icon" type="image/png" sizes="16x16"
          href="${pageContext.request.contextPath }/images/RPGTask.png">
    <meta http-equiv=Content-Type content="text/html; charset=utf-8">
    <link href="${pageContext.request.contextPath}/css/reset.css" type=text/css rel=stylesheet>
    <link href="${pageContext.request.contextPath}/css/pixel_style.css" type=text/css rel=stylesheet>
    <link href="${pageContext.request.contextPath}/css/pixel_reg.css" type=text/css rel=stylesheet>
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
    <script>
        let icon_num = ${icon_num};
        let sex = ${sex};
        $().ready(function () {
            var $male = $("#male_icon>img");  // 申明一个数组变量，存放这些图片
            var $female = $("#female_icon>img");  // 申明一个数组变量，存放这些图片
            if (sex) {
                $female[icon_num].style.display = "block";
            } else {
                $male[icon_num].style.display = "block";
            }
            $("#next").click(function () {
                if (sex) {
                    $male[icon_num].style.display = "none";
                    $female[icon_num].style.display = "none";
                    icon_num = (icon_num + 1) % 50;
                    $female[icon_num].style.display = "block";   // 展示当前选择的图片
                } else {
                    $male[icon_num].style.display = "none";
                    $female[icon_num].style.display = "none";
                    icon_num = (icon_num + 1) % 50;
                    $male[icon_num].style.display = "block";   // 展示当前选择的图片
                }
                $("#iconId").val(icon_num);
            });
            $("#pre").click(function () {
                if (sex) {
                    $male[icon_num].style.display = "none";
                    $female[icon_num].style.display = "none";
                    if (icon_num <= 0)
                        icon_num = 49;
                    else
                        icon_num = icon_num - 1;
                    $female[icon_num].style.display = "block";   // 展示当前选择的图片
                } else {
                    $male[icon_num].style.display = "none";
                    $female[icon_num].style.display = "none";
                    if (icon_num <= 0)
                        icon_num = 49;
                    else
                        icon_num = icon_num - 1;
                    $male[icon_num].style.display = "block";   // 展示当前选择的图片
                }
                $("#iconId").val(icon_num);
            });
            $("#female").click(function () {
                if (sex === false) {
                    $male[icon_num].style.display = "none";
                    $female[icon_num].style.display = "block";
                }
                sex = true;
                $("#sex").val("true");
            });
            $("#male").click(function () {
                if (sex === true) {
                    $female[icon_num].style.display = "none";
                    $male[icon_num].style.display = "block";
                }
                sex = false;
                $("#sex").val("false");
            });
        });
    </script>
</head>
<body>
<form class="reg_box rpg_font center" action="${pageContext.request.contextPath }/register.action"
      method="post">

    <div id="female_icon" class="female_icon">
        <c:forEach items="${icon_list}" var="l">
            <img src="${pageContext.request.contextPath }/images/character/female/female${l.toString()}.png">
        </c:forEach>
    </div>
    <div id="male_icon" class="male_icon">
        <c:forEach items="${icon_list}" var="l">
            <img src="${pageContext.request.contextPath }/images/character/male/male${l.toString()}.png">
        </c:forEach>
    </div>
    <ul class="reg_content">
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
            <label class="rpg_3h" for="nickname">昵称</label>
            <input class="rpg_3h rpg_font" type="text" id="nickname" name="nickname" placeholder="请输入昵称"
                   required>
        </li>
        <li>
            <button class="rpg_4h rpg_font reg_button" type="button"
                    onclick=location.href="${pageContext.request.contextPath}/login.action">返回
            </button>
            <button class="rpg_4h rpg_font reg_button" type="submit">注册</button>
        </li>
        <li>
            <span class="rpg_3h rpg_font" style="color: red;">${msg}</span>
        </li>
    </ul>
    <input id="sex" name="sex" type="hidden" value="false">
    <input id="iconId" name="iconId" type="hidden" value="0">
    <button class="pre_button" id="pre" type="button"></button>
    <button class="next_button" id="next" type="button"></button>
    <button class="male_button" id="male" type="button"></button>
    <button class="female_button" id="female" type="button"></button>
</form>
</body>
</html>
