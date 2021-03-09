<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>校园互助系统</title>
    <link rel="icon" type="image/png" sizes="16x16"
          href="${pageContext.request.contextPath }/images/origin/RPGTask.png">
    <meta http-equiv=Content-Type content="text/html; charset=utf-8">
    <link href="${pageContext.request.contextPath}/css/reset.css" type=text/css rel=stylesheet>
    <link href="${pageContext.request.contextPath}/css/pixel_style.css" type=text/css rel=stylesheet>
    <link href="${pageContext.request.contextPath}/css/main_style.css" type=text/css rel=stylesheet>
</head>
<body>
<header>
    <div style="height: 80px"></div>
</header>
<div class="main_content">
    <aside>
        <div class="user_profile">
            <div class="head_frame">
                <img class="title" src="${pageContext.request.contextPath}/images/title.png">
                <img class="frame"
                     src="${pageContext.request.contextPath}/images/character/female/female${desc.icon}.png">
            </div>
            <ul>
                <li class="mid">
                    <p class="rpg_font rpg_3h">${desc.nickName}</p>
                    <c:if test="${desc.sex==true}">
                        <img class="sex rpg_font rpg_3h" src="${pageContext.request.contextPath}/images/female.png">
                    </c:if>
                    <c:if test="${desc.sex==false}">
                        <img class="sex rpg_font rpg_3h" src="${pageContext.request.contextPath}/images/male.png">
                    </c:if>
                    <p class="rpg_font rpg_3h">LV:0</p>
                </li>
                <li>
                    <div class="exp_bar exp">
                        <div class="exp_content">
                            <img class="exp" src="${pageContext.request.contextPath}/images/exp.png">
                            <p class="rpg_font rpg_1h exp_num">${desc.exp}/100</p>
                        </div>
                    </div>

                </li>
                <li class="mid">
                    <p class="rpg_font rpg_3h"><b>${desc.coin}</b></p>
                    <img class="sex rpg_font rpg_3h" src=" ${pageContext.request.contextPath}/images/coin.png">
                </li>
                <li>
                    <c:if test="${desc.signature.length() == 0}">
                        <p class="signature rpg_font rpg_3h">你还没有设置个性签名。<br/>立即前往生涯设置吧!</p>
                    </c:if>
                    <c:if test="${desc.signature.length() != 0}">
                        <p class="signature rpg_font rpg_3h">${desc.signature}</p>
                    </c:if>
                </li>
            </ul>
            <label style="visibility: hidden">${desc.title}</label>
        </div>
        <div class="menu">
            <ul>
                <li>
                    <img src="${pageContext.request.contextPath }/images/task_icon.png">
                    <a class="rpg_4h rpg_font" href="${pageContext.request.contextPath}/task.action">任务</a>
                </li>
                <li>
                    <img src="${pageContext.request.contextPath }/images/blog_icon.png">
                    <a class="rpg_4h rpg_font" href="${pageContext.request.contextPath}/blog.action">博客</a>
                </li>
                <li>
                    <img src="${pageContext.request.contextPath }/images/mail_icon.png">
                    <a class="rpg_4h rpg_font" href="${pageContext.request.contextPath}/mail.action">信箱</a>
                </li>
                <li>
                    <img src="${pageContext.request.contextPath }/images/user_icon.png">
                    <a class="rpg_4h rpg_font" href="${pageContext.request.contextPath}/user.action">生涯</a>
                </li>
                <li>
                    <img src="${pageContext.request.contextPath }/images/friend_icon.png">
                    <a class="rpg_4h rpg_font" href="${pageContext.request.contextPath}/friend.action">关注</a>
                </li>
                <c:if test="${USER_SESSION.userType == false}">
                    <li>
                        <img src="${pageContext.request.contextPath }/images/judge_icon.png">
                        <a class="rpg_4h rpg_font" href="${pageContext.request.contextPath}/judge.action">审核</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </aside>
    <mian>


