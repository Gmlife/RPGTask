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
          href="${pageContext.request.contextPath }/images/RPGTask.png">
    <meta http-equiv=Content-Type content="text/html; charset=utf-8">
    <link href="${pageContext.request.contextPath}/css/reset.css" type=text/css rel=stylesheet>
    <link href="${pageContext.request.contextPath}/css/pixel_style.css" type=text/css rel=stylesheet>
    <link href="${pageContext.request.contextPath}/css/main_style.css" type=text/css rel=stylesheet>
    <link href="${pageContext.request.contextPath}/css/task.css" type=text/css rel=stylesheet>
    <link href="${pageContext.request.contextPath}/css/user.css" type=text/css rel=stylesheet>
    <link href="${pageContext.request.contextPath}/css/side_bar.css" type=text/css rel=stylesheet>
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/js/ueditor-1.4.3.3/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/js/ueditor-1.4.3.3/_examples/editor_api.js"></script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/js/ueditor-1.4.3.3/lang/zh-cn/zh-cn.js"></script>
</head>
<script>
    $().ready(function () {
        $("#exp_num")[0].style.width = "${desc.exp}%";
    });

    function sign(uid) {
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/signed.action",
            data: {"uid": uid},
            dataType: "json",
            success: function () {
                location.reload();
            },
            error: function () {
                alert("error");
            }
        });
    }
</script>
<body>
<header>
    <div style="height: 80px"></div>
</header>
<div class="main_content">
    <aside>
        <div class="user_profile">
            <div class="head_frame">
                <img class="title" src="${pageContext.request.contextPath}/images/common_ui/title.png">
                <c:if test="${desc.sex==true}">
                    <img class="frame"
                         src="${pageContext.request.contextPath}/images/character/female/female${desc.icon}.png">
                </c:if>
                <c:if test="${desc.sex==false}">
                    <img class="frame"
                         src="${pageContext.request.contextPath}/images/character/male/male${desc.icon}.png">
                </c:if>
            </div>
            <ul>
                <li class="mid">
                    <p class="rpg_font rpg_3h inline">${desc.nickName}</p>
                    <c:if test="${desc.sex==true}">
                        <img class="rpg_font rpg_3h icon_3h"
                             src="${pageContext.request.contextPath}/images/common_ui/female.png">
                    </c:if>
                    <c:if test="${desc.sex==false}">
                        <img class="rpg_font rpg_3h icon_3h"
                             src="${pageContext.request.contextPath}/images/common_ui/male.png">
                    </c:if>
                    <p class="rpg_font rpg_3h inline">LV:0</p>
                </li>
                <li>
                    <div class="exp_bar exp">
                        <div class="exp_content">
                            <img class="exp" id="exp_num"
                                 src="${pageContext.request.contextPath}/images/common_ui/exp.png">
                            <p class="rpg_font rpg_1h exp_num">${desc.exp}/100</p>
                        </div>
                    </div>

                </li>
                <li class="mid">
                    <p class="rpg_font rpg_3h gold inline">${desc.coin}</p>
                    <img class="icon_3h inline"
                         src="${pageContext.request.contextPath}/images/common_ui/coin.png">
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
        <div class="day_signed">
            <p class="rpg_font rpg_4h">当前已签到<b class="rpg_font rpg_4h red">${u_task.signedDay}</b>天</p>
            <c:if test="${can_sign == true}">
                <button class="rpg_4h rpg_font" id="sign_bt" onclick="sign(${desc.uid})">签到</button>
            </c:if>
            <c:if test="${can_sign == false}">
                <button class="rpg_4h rpg_font" id="sign_bt" style="pointer-events: none;">已签到</button>
            </c:if>
        </div>
        <div class="menu">
            <ul>
                <li>
                    <img src="${pageContext.request.contextPath }/images/menu/task_icon.png">
                    <a class="rpg_4h rpg_font" href="${pageContext.request.contextPath}/task.action">任务</a>
                </li>
                <li>
                    <img src="${pageContext.request.contextPath }/images/menu/blog_icon.png">
                    <a class="rpg_4h rpg_font" href="${pageContext.request.contextPath}/blog.action">博客</a>
                </li>
                <li>
                    <img src="${pageContext.request.contextPath }/images/menu/mail_icon.png">
                    <a class="rpg_4h rpg_font" href="${pageContext.request.contextPath}/mail.action">信箱</a>
                </li>
                <li>
                    <img src="${pageContext.request.contextPath }/images/menu/user_icon.png">
                    <a class="rpg_4h rpg_font" href="${pageContext.request.contextPath}/user.action">生涯</a>
                </li>
                <li>
                    <img src="${pageContext.request.contextPath }/images/menu/friend_icon.png">
                    <a class="rpg_4h rpg_font" href="${pageContext.request.contextPath}/friend.action">关注</a>
                </li>
                <li>
                    <img src="${pageContext.request.contextPath }/images/common_ui/quit.png">
                    <a class="rpg_4h rpg_font" href="${pageContext.request.contextPath}/logout.action">退出</a>
                </li>
                <c:if test="${USER_SESSION.userType == true}">
                    <li>
                        <img src="${pageContext.request.contextPath }/images/menu/judge_icon.png">
                        <a class="rpg_4h rpg_font" href="${pageContext.request.contextPath}/judge.action">审核</a>
                    </li>
                </c:if>
            </ul>
        </div>
        <c:if test="${cur_page == 'task'}">
            <form class="task_edit" action="${pageContext.request.contextPath}/add_task.action" method="post">
                <p>test</p>
            </form>>
        </c:if>
    </aside>
    <main>


