<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录页面 - 校园互助系统</title>
    <link rel="icon" type="image/png" sizes="16x16"
          href="${pageContext.request.contextPath }/images/origin/RPGTask.png">
    <meta http-equiv=Content-Type content="text/html; charset=utf-8">
    <link href="${pageContext.request.contextPath}/css/reset.css" type=text/css rel=stylesheet>
    <link href="${pageContext.request.contextPath}/css/pixel_style.css" type=text/css rel=stylesheet>
    <link href="${pageContext.request.contextPath}/css/main_style.css" type=text/css rel=stylesheet>
</head>
<body>
<!-- 导航栏部分 -->
<header>
    <div class="head_content">
        <div style="display: block;height: 80px">
        </div>
        <div class="menu">
            <ul>
                <li>
                    <img src="${pageContext.request.contextPath }/images/task_icon.png">
                    <a class="rpg_4h rpg_font">任务</a>
                </li>
                <li>
                    <img src="${pageContext.request.contextPath }/images/blog_icon.png">
                    <a class="rpg_4h rpg_font">博客</a>
                </li>
                <li>
                    <img src="${pageContext.request.contextPath }/images/mail_icon.png">
                    <a class="rpg_4h rpg_font">信箱</a>
                </li>
                <li>
                    <img src="${pageContext.request.contextPath }/images/user_icon.png">
                    <a class="rpg_4h rpg_font">生涯</a>
                </li>
                <li>
                    <img src="${pageContext.request.contextPath }/images/friend_icon.png">
                    <a class="rpg_4h rpg_font">羁绊</a>
                </li
                >
                <c:if test="${USER_SESSION.userType==false}">
                <li>
                    <img src="${pageContext.request.contextPath }/images/judge_icon.png">
                    <a class="rpg_4h rpg_font">审核</a>
                </li>
                </c:if>
            </ul>
        </div>
    </div>
</header>
<!-- 左侧显示列表部分 start-->
<%@include  file="../WEB-INF/footer.jsp"%>
<!-- 左侧显示列表部分 end-->




