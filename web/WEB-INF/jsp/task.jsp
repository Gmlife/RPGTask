<%@include file="/WEB-INF/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<script>
    function deleteTask(tid) {
        var res = confirm("真的要删除该任务吗?");
        if (res === true) {
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/delete_task.action",
                data: {"tid": tid},
                dataType: "json",
                success: function () {
                    location.reload();
                },
                error: function () {
                    alert("error");
                }
            })
        }
    }
</script>
<div class="task_list">
    <p class="main_title rpg_font rpg_5h">任务列表</p>
    <ul>
        <c:forEach items="${task_list}" var="t">
            <c:if test="${t.verifyState == true}">
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
                        <c:if test="${t.publishUserId !=desc.uid}">
                            <c:if test="${t.taskState == 0}">
                                <button class="rpg_3h rpg_font submit">领取</button>
                            </c:if>
                            <c:if test="${t.taskState == 1}">
                                <p class="rpg_3h rpg_font progress">进行中</p>
                            </c:if>
                            <c:if test="${t.taskState == 2}">
                                <p class="rpg_3h rpg_font finish">已完成</p>
                            </c:if>
                        </c:if>
                        <c:if test="${t.publishUserId ==desc.uid}">
                            <button class="rpg_3h rpg_font" onclick="deleteTask(${t.taskId})">删除</button>
                        </c:if>
                    </div>
                </li>
            </c:if>
        </c:forEach>
    </ul>
</div>

<%@include file="/WEB-INF/footer.jsp" %>
