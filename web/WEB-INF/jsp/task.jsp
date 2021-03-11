<%@include file="/WEB-INF/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<div class="task_list">
    <p class="task_title rpg_font rpg_5h">任务列表</p>
    <ul>
        <c:forEach items="${task_list}" var="t">
            <c:if test="${t.verifyState == true}">
                <li>
                    <div class="task_head">
                        <h1 class="rpg_4h rpg_font">${t.taskTitle}</h1>
                        <img class="task_icon" src="${pageContext.request.contextPath }/images/task/task_icon${t.taskIcon}.png">
                    </div>
                    <div class="task_content">
                        <p class="rpg_3h rpg_font">${t.taskDesc}</p>
                    </div>
                    <div class="task_foot">
                        <div class="award">
                            <p class="rpg_3h rpg_font red inline">${t.awardCoin}</p>
                            <img class="inline icon_3h" src="${pageContext.request.contextPath }/images/common_ui/coin.png">
                        </div>
                        <c:if test="${t.taskState == 0}">
                            <button class="rpg_3h rpg_font submit">领取</button>
                        </c:if>
                        <c:if test="${t.taskState == 1}">
                            <p class="rpg_3h rpg_font progress">进行中</p>
                        </c:if>
                        <c:if test="${t.taskState == 2}">
                            <p class="rpg_3h rpg_font finish">已完成</p>
                        </c:if>
                    </div>
                </li>
            </c:if>
        </c:forEach>
    </ul>
</div>

<%@include file="/WEB-INF/footer.jsp" %>