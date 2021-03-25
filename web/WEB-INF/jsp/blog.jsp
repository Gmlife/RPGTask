<%@include file="/WEB-INF/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<link href="<%=base%>/css/mail.css" type=text/css rel=stylesheet>
<script>
</script>
<div class="content">
    <p class="main_title rpg_font rpg_5h">博客列表</p>
    <ul>
        <c:forEach items="${message_list}" var="m">
            <li>
                <c:if test="${mid2user.get(m.messageId).sex == true}">
                    <div class="icon">
                        <img title="查看用户详细信息"
                             src="<%=base%>/images/character/female/female${mid2user.get(m.messageId).icon}.png"
                             onclick="location.href='<%=base%>/${m.messageUserId}/main.action'"/>
                    </div>
                </c:if>
                <c:if test="${mid2user.get(m.messageId).sex == false}">
                    <div class="icon">
                        <img title="查看用户详细信息"
                             src="<%=base%>/images/character/male/male${mid2user.get(m.messageId).icon}.png"
                             onclick="location.href='<%=base%>/${m.messageUserId}/main.action'"/>
                    </div>
                </c:if>
                <p class="mail_content rpg_font rpg_4h">${m.messageText}</p>
                <a class="smallBtn rpg_font rpg_4h red" onclick="deleteMessage(${m.messageId})">×</a>
            </li>
        </c:forEach>
    </ul>
</div>


<%@include file="/WEB-INF/footer.jsp" %>