<%@include file="/WEB-INF/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<script>
    const chara_path = "${pageContext.request.contextPath }/images/character";
    $().ready(function () {
        $("#iconId").val("${desc.icon}");
        if (${desc.sex == true})
            $("#cur_icon")[0].src = chara_path + "/female/female" + ${desc.icon}+".png";
        else
            $("#cur_icon")[0].src = chara_path + "/male/male" + ${desc.icon}+".png";
        const li = $("#icon_selector>li");
        for (let i = 1; i <= li.length; i++) {
            $(li[i - 1]).click(function () {
                if (${desc.sex == true})
                    $("#cur_icon")[0].src = chara_path + "/female/female" + i.toString() + ".png";
                else
                    $("#cur_icon")[0].src = chara_path + "/male/male" + i.toString() + ".png";
                $("#iconId").val(i);
            })
        }
    })

    function updateUser() {
        $.post("${pageContext.request.contextPath}/user/update.action", $("#desc_editor").serialize(), function (data) {
            if (data === "ok") {
                alert("保存成功！");
                location.reload();
            } else {
                alert("保存失败！");
            }
        })
    }
</script>
<div class="user_editor">
    <form class="desc_editor" id="desc_editor" onsubmit="return false">
        <p class="main_title rpg_font rpg_5h">用户详情编辑</p>
        <ul class="icon_selector" id="icon_selector">
            <c:if test="${desc.sex==true}">
                <c:forEach items="${icon_list}" var="l">
                    <li id="p${l}">
                        <img src="${pageContext.request.contextPath }/images/character/female/female${l.toString()}.png">
                    </li>
                </c:forEach>
            </c:if>
            <c:if test="${desc.sex==false}">
                <c:forEach items="${icon_list}" var="l">
                    <li id="p${l}">
                        <img src="${pageContext.request.contextPath }/images/character/male/male${l.toString()}.png">
                    </li>
                </c:forEach>
            </c:if>
        </ul>
        <div class="icon_edit" id="editor">
            <img id="cur_icon"/>
        </div>
        <ul class="user_other">
            <li>
                <label class="rpg_font rpg_3h" for="nickname">昵称</label>
                <input class="rpg_font rpg_3h rpg_font" type="text" id="nickname" name="nickname" required
                       autofocus value="${desc.nickName}">
            </li>
            <li>
                <label class="rpg_font rpg_3h" for="signature">个性签名</label>
                <textarea class="rpg_font rpg_3h rpg_font" type="text" id="signature" name="signature" required
                          autofocus>${desc.signature}</textarea>
            </li>

        </ul>
        <input id="uid" name="uid" type="hidden" value="${desc.uid}">
        <input id="iconId" name="iconId" type="hidden" value="0">
    </form>
    <button class="rpg_4h rpg_font save_button" onclick="updateUser()">保存</button>
    <div class="user_tb_info">
        <div class="doing_task">
            <p class="user_task_title rpg_font rpg_5h">进行中的任务</p>
            <ul>
                <c:forEach items="${tasks}" var="t">
                    <li>
                        <img src="${pageContext.request.contextPath}/images/task/task_icon${t.taskIcon}.png">
                        <p class="task_title rpg_font rpg_8h" title="${t.taskDesc}">${t.taskTitle}</p>
                        <img class="coin" src="${pageContext.request.contextPath}/images/common_ui/coin.png">
                        <p class="task_award rpg_font rpg_3h">${t.awardCoin}</p>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="blog_list">
            <p class="user_blog_title rpg_font rpg_5h">我发布的博客</p>
            <ul>
                <c:forEach items="${blogs}" var="b">
                    <li>
                        <a class="blog_title rpg_font rpg_8h"
                           href="${pageContext.request.contextPath}/blog/${b.blogId}">${b.blogTitle}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/footer.jsp" %>