<%@include file="/WEB-INF/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<link href="<%=base%>/css/task.css" type=text/css rel=stylesheet>
<script>
    function refuseTask(tid) {
        const res = confirm("真的要拒绝该任务吗?");
        if (res === true) {
            $.post("<%=base%>/task/refuse.action", {
                "a_uid":${my_desc.uid},
                "tid": tid
            }, function (data) {
                if (data === "ok") {
                    alert("拒绝成功！");
                    location.reload();
                } else {
                    alert("拒绝失败!");
                }

            })
        }
    }

    function acceptTask(tid) {
        const res = confirm("真的要批准该任务吗?");
        if (res === true) {
            $.post("<%=base%>/task/accept.action", {
                "a_uid":${my_desc.uid},
                "tid": tid
            }, function (data) {
                if (data === "ok") {
                    alert("批准成功！");
                    location.reload();
                } else {
                    alert("批准失败!");
                }

            })
        }
    }
</script>
<div class="content">
    <p class="main_title rpg_font rpg_5h">审核列表</p>
    <div class="task_info_list">
        <c:forEach items="${task_list}" var="t">
            <div class="task_info">
                <div class="task_head">
                    <h1 class="rpg_4h rpg_font">${t.taskTitle}</h1>
                    <img class="task_icon"
                         src="<%=base%>/images/task/task_icon${t.taskIcon}.png">
                </div>
                <div class="task_content">
                    <p class="rpg_3h rpg_font">${t.taskDesc}</p>
                </div>
                <div class="task_foot">
                    <div class="award">
                        <p class="rpg_3h rpg_font red inline">${t.awardCoin}</p>
                        <img class="inline icon_3h"
                             src="<%=base%>/images/common_ui/coin.png">
                    </div>
                    <button class="rpg_3h rpg_font smallBtn red" onclick="refuseTask(${t.taskId})">×</button>
                    <button class="rpg_3h rpg_font smallBtn green" onclick="acceptTask(${t.taskId})">√</button>
                </div>
            </div>
        </c:forEach>
    </div>
</div>


<%@include file="/WEB-INF/footer.jsp" %>