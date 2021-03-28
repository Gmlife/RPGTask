<%@include file="/WEB-INF/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<link href="<%=base%>/css/user.css" type=text/css rel=stylesheet>
<script>
    function unFollowUser(f_uid) {
        const res = confirm("确认取关该用户？");
        if (res === true) {
            $.post("<%=base%>/user/unFollow.action", {"uid":${my_desc.uid}, "f_uid": f_uid}, function (data) {
                if (data === "ok") {
                    alert("取关成功！");
                    location.reload();
                } else if (data === "is_not_follow") {
                    alert("该用户不在你的关注列表里！");
                } else {
                    alert("取关失败！");
                }
            })
        }
    }

    function followUser(f_uid) {
        const res = confirm("确认关注该用户？");
        if (res === true) {
            $.post("<%=base%>/user/follow.action", {"uid":${my_desc.uid}, "f_uid": f_uid}, function (data) {
                if (data === "ok") {
                    alert("关注成功！");
                    location.reload();
                } else if (data === "is_follow") {
                    alert("该用户已经在你的关注列表里了！");
                } else {
                    alert("关注失败！");
                }
            })
        }
    }

    function receiveTask(tid) {
        const res = confirm("是否领取该任务？");
        if (res === true) {
            $.post("<%=base%>/task/receive.action", {"r_uid":${my_desc.uid}, "tid": tid}, function (data) {
                if (data === "ok") {
                    alert("领取成功");
                    location.reload();
                } else {
                    alert("领取失败");
                }
            })
        }

    }

    function banUser() {
        const res = confirm("确认封禁该用户？");
        if (res === true) {
            $.post("<%=base%>/user/ban.action", {"a_uid":${my_desc.uid}, "ban_uid":${other_desc.uid}}, function (data) {
                if (data === "ok") {
                    alert("封禁成功");
                    location.reload();
                } else {
                    alert("封禁失败");
                }
            });
        }
    }

    function unBanUser() {
        const res = confirm("确认解封该用户？");
        if (res === true) {
            $.post("<%=base%>/user/unBan.action", {"a_uid":${my_desc.uid}, "ban_uid":${other_desc.uid}}, function (data) {
                if (data === "ok") {
                    alert("解封成功");
                    location.reload();
                } else {
                    alert("解封失败");
                }
            });
        }
    }
</script>
<div class="content">
    <div class="desc_editor">
        <p class="main_title rpg_font rpg_5h">用户详情</p>
        <div class="other_icon" id="editor">
            <c:if test="${other_desc.sex == true}">
                <img
                        src="<%=base%>/images/character/female/female${other_desc.icon}.png"/>
            </c:if>
            <c:if test="${other_desc.sex == false}">
                <img
                        src="<%=base%>/images/character/male/male${other_desc.icon}.png"/>
            </c:if>
            <c:if test="${other_user.isBan==true}">
                <img style="z-index: 1" title="该用户已被封禁" src="<%=base%>/images/common_ui/ban.png">
            </c:if>
        </div>
        <ul class="user_other_o">
            <li>
                <label class="rpg_font rpg_3h">用户名</label>
                <p class="name rpg_font rpg_4h rpg_font green">${other_user.userName}</p>
                <label class="rpg_font rpg_3h">昵称</label>
                <p class="name rpg_font rpg_4h rpg_font green">${other_desc.nickName}</p>
            </li>
            <li>
                <label class="rpg_font rpg_3h">个性签名</label>
                <p class="signature rpg_font rpg_3h rpg_font">${other_desc.signature}</p>
            </li>
        </ul>
        <c:if test="${is_follow == true}">
            <button class="rpg_4h rpg_font follow_button" onclick="unFollowUser(${other_user.uid})">取关</button>
        </c:if>
        <c:if test="${is_follow == false}">
            <button class="rpg_4h rpg_font follow_button" onclick="followUser(${other_user.uid})">关注</button>
        </c:if>
    </div>
    <div class="user_tb_info">
        <div class="cell">
            <p class="cell_title rpg_font rpg_3h">TA发布的任务</p>
            <ul>
                <c:forEach items="${other_p_tasks}" var="t">
                    <li>
                        <img src="<%=base%>/images/task/task_icon${t.taskIcon}.png">
                        <p class="task_title rpg_font rpg_4h" title="${t.taskDesc}">${t.taskTitle}</p>
                        <div class="bt_group">
                            <c:if test="${t.verifyState == true}">
                                <c:if test="${t.taskState == 0}">
                                    <button title="领取任务" class="smallBtn rpg_font rpg_2h green"
                                            onclick="receiveTask(${t.taskId})">√
                                    </button>
                                </c:if>
                            </c:if>
                        </div>

                        <c:if test="${t.verifyState == false}">
                            <p class="hint rpg_font rpg_2h">尚未通过审核</p>
                        </c:if>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="cell">
            <p class="cell_title rpg_font rpg_3h">TA进行中的任务</p>
            <ul>
                <c:forEach items="${other_tasks}" var="t">
                    <li>
                        <img src="<%=base%>/images/task/task_icon${t.taskIcon}.png">
                        <p class="task_title rpg_font rpg_4h" title="${t.taskDesc}">${t.taskTitle}</p>
                        <img class="coin" src="<%=base%>/images/common_ui/coin.png">
                        <p class="task_award rpg_font rpg_3h">${t.awardCoin}</p>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="cell">
            <p class="cell_title rpg_font rpg_3h">TA发布的博客</p>
            <ul>
                <c:forEach items="${other_blogs}" var="b">
                    <li>
                        <a class="blog_title rpg_font rpg_4h"
                           href="<%=base%>/blog/${b.blogId}">${b.blogTitle}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/footer.jsp" %>