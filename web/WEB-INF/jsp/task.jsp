<%@include file="/WEB-INF/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="main_content">
    <div class="task_list">

    </div>
    <div class="user_profile">
        <img src="${pageContext.request.contextPath}/images/character/female/female${desc.icon}.png">
        <label>${desc.nickName}</label>
        <label>${desc.coin}</label>
        <label>${desc.exp}</label>
        <label>${desc.sex}</label>
        <label>${desc.signature}</label>
        <label>${desc.title}</label>
    </div>
</div>
<%@include file="/WEB-INF/footer.jsp" %>
