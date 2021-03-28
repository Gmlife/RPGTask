<%@ page language="java" contentType="text/html; charset=UTF-8"

         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String base = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + path;
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>校园互助系统</title>
    <link rel="icon" type="image/png" sizes="16x16"
          href="<%=base%>/images/RPGTask.png">
    <meta http-equiv=Content-Type content="text/html; charset=utf-8">
    <link href="<%=base%>/css/reset.css" type=text/css rel=stylesheet>
    <link href="<%=base%>/css/pixel_style.css" type=text/css rel=stylesheet>
    <link href="<%=base%>/css/main_style.css" type=text/css rel=stylesheet>
    <link href="<%=base%>/css/side_bar.css" type=text/css rel=stylesheet>
    <script src="<%=base%>/js/jquery-1.11.3.min.js"></script>
    <script src="<%=base%>/js/jquery.dataTables.min.js"></script>
</head>
<script>
    $().ready(function () {
        $("#exp_num")[0].style.width = "${my_desc.exp}%";
    });

    function sign(uid) {
        $.post("<%=base%>/signed.action", {"uid": uid}, function (data) {
            if (data === "ok") {
                alert("签到成功！");
                location.reload();
            } else {
                alert("签到失败!");
            }
        })
    }

    function addTask() {
        let check = true;
        if ($("#task_title").val() === "") check = false;
        if ($("#task_desc").val() === "") check = false;
        if ($("#task_award").val() === "") check = false;
        if (check) {
            $.post("<%=base%>/task/add.action", $("#task_editor").serialize(), function (data) {
                if (data === "ok") {
                    alert("发布成功！请等待管理员审核！");
                    location.reload();
                } else if (data === "fail") {
                    alert("发布失败!");
                } else if (data === "coin not enough") {
                    alert("金币不足，无法发布");
                }
            })
        } else {
            alert("不能有空字段!");
        }
    }

    function sendMessage() {
        let check = true;
        if ($("#m_target").val() === "") check = false;
        if ($("#m_text").val() === "") check = false;
        if (check) {
            $.post("<%=base%>/mail/send.action", $("#message_editor").serialize(), function (data) {
                if (data === "ok") {
                    alert("消息发送成功");
                    location.reload();
                } else if (data === "fail") {
                    alert("消息发送失败!");
                } else if (data === "no user") {
                    alert("发送目标的uid或者账号不存在，请重新核实后再发送");
                }
            })
        } else {
            alert("不能有空字段!");
        }
    }
    function addBlog() {
        let check = true;
        if ($("#blog_title").val() === "") check = false;
        if ($("#blog_text").val() === "") check = false;
        if (check) {
            $.post("<%=base%>/blog/add.action", $("#blog_editor").serialize(), function (data) {
                if (data === "ok") {
                    alert("发布成功！请等待管理员审核！");
                    location.reload();
                } else if (data === "fail") {
                    alert("发布失败!");
                }
            })
        } else {
            alert("不能有空字段!");
        }
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
                <img class="title" src="<%=base%>/images/common_ui/title.png">
                <c:if test="${my_desc.sex==true}">
                    <img class="frame"
                         src="<%=base%>/images/character/female/female${my_desc.icon}.png">
                </c:if>
                <c:if test="${my_desc.sex==false}">
                    <img class="frame"
                         src="<%=base%>/images/character/male/male${my_desc.icon}.png">
                </c:if>
            </div>
            <ul>
                <li class="mid">
                    <p class="rpg_font rpg_3h inline">${my_desc.nickName}</p>
                    <c:if test="${my_desc.sex==true}">
                        <img class="rpg_font rpg_3h icon_3h"
                             src="<%=base%>/images/common_ui/female.png">
                    </c:if>
                    <c:if test="${my_desc.sex==false}">
                        <img class="rpg_font rpg_3h icon_3h"
                             src="<%=base%>/images/common_ui/male.png">
                    </c:if>
                    <p class="rpg_font rpg_3h inline">LV:0</p>
                </li>
                <li>
                    <div class="exp_bar exp">
                        <div class="exp_content">
                            <img class="exp" id="exp_num"
                                 src="<%=base%>/images/common_ui/exp.png">
                            <p class="rpg_font rpg_1h exp_num">${my_desc.exp}/100</p>
                        </div>
                    </div>

                </li>
                <li class="mid">
                    <p class="rpg_font rpg_3h gold inline">${my_desc.coin}</p>
                    <img class="icon_3h inline"
                         src="<%=base%>/images/common_ui/coin.png">
                </li>
                <li>
                    <c:if test="${my_desc.signature.length() == 0}">
                        <p class="signature rpg_font rpg_3h">你还没有设置个性签名。<br/>立即前往生涯设置吧!</p>
                    </c:if>
                    <c:if test="${my_desc.signature.length() != 0}">
                        <p class="signature rpg_font rpg_3h">${my_desc.signature}</p>
                    </c:if>
                </li>
            </ul>
            <label style="visibility: hidden">${my_desc.title}</label>
        </div>
        <div class="day_signed">
            <p class="rpg_font rpg_4h">当前已签到<b class="rpg_font rpg_4h red">${u_task.signedDay}</b>天</p>
            <c:if test="${can_sign == true}">
                <button class="rpg_4h rpg_font" id="sign_bt" onclick="sign(${my_desc.uid})">签到</button>
            </c:if>
            <c:if test="${can_sign == false}">
                <button class="rpg_4h rpg_font" id="sign_bt" style="pointer-events: none;">已签到</button>
            </c:if>
        </div>
        <div class="menu">
            <ul>
                <li>
                    <img src="<%=base%>/images/menu/task_icon.png">
                    <a class="rpg_4h rpg_font" href="<%=base%>/task.action?sort=Date">任务</a>
                </li>
                <li>
                    <img src="<%=base%>/images/menu/blog_icon.png">
                    <a class="rpg_4h rpg_font" href="<%=base%>/blog.action">博客</a>
                </li>
                <li>
                    <img src="<%=base%>/images/menu/mail_icon.png">
                    <a class="rpg_4h rpg_font" href="<%=base%>/mail.action">信箱</a>
                </li>
                <li>
                    <img src="<%=base%>/images/menu/user_icon.png">
                    <a class="rpg_4h rpg_font" href="<%=base%>/user.action">生涯</a>
                </li>
                <li>
                    <img src="<%=base%>/images/menu/friend_icon.png">
                    <a class="rpg_4h rpg_font" href="<%=base%>/follow.action">关注</a>
                </li>
                <li>
                    <img src="<%=base%>/images/common_ui/quit.png">
                    <a class="rpg_4h rpg_font" href="<%=base%>/logout.action">退出</a>
                </li>
                <c:if test="${USER_SESSION.userType == true}">
                    <li>
                        <img src="<%=base%>/images/menu/judge_icon.png">
                        <a class="rpg_4h rpg_font" href="<%=base%>/judge.action">审核</a>
                    </li>
                </c:if>
            </ul>
        </div>
        <c:if test="${cur_page == 'task'}">
            <form class="send_area" id="task_editor" onsubmit="return false">
                <input class="s_title rpg_font rpg_font rpg_3h" type="text" id="task_title" name="task_title"
                       placeholder="在此输入任务标题" required>
                <textarea class="s_text rpg_font rpg_3h" id="task_desc" name="task_desc" placeholder="在此输入任务描述"
                          required></textarea>
                <input class="a_award rpg_font rpg_3h" type="number" id="task_award" name="task_award"
                       placeholder="奖励金额" required>
                <img class="icon_3h" src="<%=base%>/images/common_ui/coin.png">
                <input type="hidden" id="p_uid" name="p_uid" value="${my_desc.uid}">
            </form>
            <button class="rpg_font rpg_2h side_button" onclick="addTask()">发布任务</button>
        </c:if>
        <c:if test="${cur_page == 'message'}">
            <form class="send_area" id="message_editor" onsubmit="return false">
                <input class="s_title rpg_font rpg_font rpg_3h" type="text" id="m_target" name="m_target"
                       placeholder="在此输入收件人uid或者用户名" required>
                <textarea class="s_text rpg_font rpg_3h" id="m_text" name="m_text" placeholder="在此输入消息文本"
                          required></textarea>
                <input type="hidden" id="s_uid" name="s_uid" value="${my_desc.uid}">
            </form>
            <button class="rpg_font rpg_2h side_button" onclick="sendMessage()">发送消息</button>
        </c:if>
        <c:if test="${USER_SESSION.userType == true}">
            <c:if test="${cur_page == 'other_info'}">
                <div class="ban_area">
                    <c:if test="${other_user.isBan==true}">
                        <button class="rpg_font rpg_2h ban_button" onclick="unBanUser()">解禁该用户</button>
                    </c:if>
                    <c:if test="${other_user.isBan==false}">
                        <button class="rpg_font rpg_2h ban_button" onclick="banUser()">封禁该用户</button>
                    </c:if>
                </div>
            </c:if>
        </c:if>
        <c:if test="${cur_page=='blog'}">
            <form class="send_area" id="blog_editor" onsubmit="return false">
                <input class="s_title rpg_font rpg_font rpg_3h" type="text" id="blog_title" name="blog_title"
                       placeholder="在此输入博客标题" required>
                <textarea class="s_text rpg_font rpg_3h" id="blog_text" name="blog_text" placeholder="在此输入博客内容"
                          required></textarea>
                <input type="hidden" id="b_uid" name="b_uid" value="${my_desc.uid}">
            </form>
            <button class="rpg_font rpg_2h side_button" onclick="addBlog()">发布博客</button>
        </c:if>
    </aside>
    <main>