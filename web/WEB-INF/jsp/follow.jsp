<%@include file="/WEB-INF/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<link href="<%=base%>/css/mail.css" type=text/css rel=stylesheet>
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
</script>
<div class="content">
    <p class="main_title rpg_font rpg_5h">我的关注</p>
    <ul>
        <c:forEach items="${other_desc}" var="o">
            <li>
                <c:if test="${o.sex == true}">
                    <div class="icon">
                        <img title="查看用户详细信息"
                             src="<%=base%>/images/character/female/female${o.icon}.png"
                             onclick="location.href='<%=base%>/${o.uid}/main.action'"/>
                    </div>
                </c:if>
                <c:if test="${o.sex == false}">
                    <div class="icon">
                        <img title="查看用户详细信息"
                             src="<%=base%>/images/character/male/male${o.icon}.png"
                             onclick="location.href='<%=base%>/${o.uid}/main.action'"/>
                    </div>
                </c:if>
                <p class="mail_content rpg_font rpg_4h">${o.nickName}:${o.signature}</p>
                <a class="smallBtn rpg_font rpg_4h red" title="取关" onclick="unFollowUser(${o.uid})">×</a>
            </li>
        </c:forEach>
    </ul>
</div>


<%@include file="/WEB-INF/footer.jsp" %>