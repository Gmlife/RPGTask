<%@include file="/WEB-INF/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<script>
    function deleteTask(tid) {
        var res = confirm("真的要删除该任务吗?");
        if (res === true) {
            $.post("${pageContext.request.contextPath}/task/delete.action", {"tid": tid}, function (data) {
                if (data === "ok") {
                    alert("删除成功！");
                    location.reload();
                } else {
                    alert("删除失败!");
                }

            })
        }
    }
</script>
<div class="task_list">
    <p class="main_title rpg_font rpg_5h">审核列表</p>
    <ul>
        <c:forEach items="${task_list}" var="t">
            <li>
                <div class="task_head">
                    <h1 class="rpg_4h rpg_font">${t.taskTitle}</h1>
                    <img class="task_icon"
                         src="${pageContext.request.contextPath }/images/task/task_icon${t.taskIcon}.png">
                </div>
                <div class="task_content">
                    <p class="rpg_3h rpg_font">${t.taskDesc}</p>
                </div>
                <div class="task_foot">
                    <div class="award">
                        <p class="rpg_3h rpg_font red inline">${t.awardCoin}</p>
                        <img class="inline icon_3h"
                             src="${pageContext.request.contextPath }/images/common_ui/coin.png">
                    </div>
                    <button class="rpg_3h rpg_font" onclick="deleteTask(${t.taskId})">通过</button>
                    <button class="rpg_3h rpg_font" onclick="deleteTask(${t.taskId})">否决</button>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>


<%@include file="/WEB-INF/footer.jsp" %>