<%@include file="/WEB-INF/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<script>
    function changeSort() {
        const task_path = "${pageContext.request.contextPath}/task.action";
        if (${sort=="发布日期"}) {
            if (${sd==null})
                location.href = (task_path + "?sort=Date_Desc");
            if (${sd=="_desc"})
                location.href = (task_path + "?sort=Date");
        }
        if (${sort=="任务奖励"}) {
            if (${sd==null})
                location.href = (task_path + "?sort=Award_Desc");
            if (${sd=="_desc"})
                location.href = (task_path + "?sort=Award");
        }
    }

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
    <p class="main_title rpg_font rpg_5h">任务列表</p>
    <div class="submenu">
        <a class="menu_button rpg_font rpg_3h" onclick="changeSort()">${sort}</a>
        <a class="drop_button"></a>
        <img class="sort_icon" onclick="changeSort()" src="${pageContext.request.contextPath}/images/common_ui/sort${sd}.png">
    </div>
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