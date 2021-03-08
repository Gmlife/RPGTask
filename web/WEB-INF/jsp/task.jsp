<%@include file="/WEB-INF/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="main_content">
    <div class="task_list">

    </div>
    <div class="user_profile">
        <img class="head_frame" src="${pageContext.request.contextPath}/images/character/female/female${desc.icon}.png">
        <ul>
            <li class="mid">
                <p class="rpg_font rpg_3h" >${desc.nickName}</p>
                <c:if test="${desc.sex==true}">
                    <img class="sex rpg_font rpg_3h" src="${pageContext.request.contextPath}/images/female.png">
                </c:if>
                <c:if test="${desc.sex==false}">
                    <img class="sex rpg_font rpg_3h" src="${pageContext.request.contextPath}/images/male.png">
                </c:if>
            </li>
            <li>
                <div class="exp_bar exp">
                    <div class="exp_content exp">
                        <img class="exp" title="当前exp:${desc.exp}" src="${pageContext.request.contextPath}/images/exp.png">
                    </div>
                </div>
            </li>
            <li class="mid">
                <p class="rpg_font rpg_3h" ><b>${desc.coin}</b></p>
                <img class="sex rpg_font rpg_3h" src=" ${pageContext.request.contextPath}/images/coin.png">
            </li>
            <li>
                <label>个性签名</label>
                <p class=" sex rpg_font rpg_3h" >${desc.signature}</p>
            </li>
        </ul>
        <label style="visibility: hidden">${desc.title}</label>
    </div>
</div>
<%@include file="/WEB-INF/footer.jsp" %>
