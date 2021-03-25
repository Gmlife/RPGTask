<%@include file="/WEB-INF/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<link href="<%=base%>/css/user.css" type=text/css rel=stylesheet>
<script>
    const chara_path = "<%=base%>/images/character";
    $().ready(function () {
        $("#iconId").val("${my_desc.icon}");
        if (${my_desc.sex == true})
            $("#cur_icon")[0].src = chara_path + "/female/female" + ${my_desc.icon}+".png";
        else
            $("#cur_icon")[0].src = chara_path + "/male/male" + ${my_desc.icon}+".png";
        const li = $("#icon_selector>li");
        for (let i = 1; i <= li.length; i++) {
            $(li[i - 1]).click(function () {
                if (${my_desc.sex == true})
                    $("#cur_icon")[0].src = chara_path + "/female/female" + i.toString() + ".png";
                else
                    $("#cur_icon")[0].src = chara_path + "/male/male" + i.toString() + ".png";
                $("#iconId").val(i);
            })
        }
    })

    function updateUser() {
        $.post("<%=base%>/user/update.action", $("#desc_editor").serialize(), function (data) {
            if (data === "ok") {
                alert("保存成功！");
                location.reload();
            } else {
                alert("保存失败！");
            }
        })
    }

    function completeTask(tid) {
        var res = confirm("真的要完成该任务吗?金币将会发送");
        if (res === true) {
            $.post("<%=base%>/task/complete.action", {"tid": tid}, function (data) {
                if (data === "ok") {
                    alert("完成成功！");
                    location.reload();
                } else {
                    alert("完成失败!");
                }

            })
        }
    }

    function deleteTask(tid) {
        var res = confirm("真的要删除该任务吗?金币将会返还");
        if (res === true) {
            $.post("<%=base%>/task/delete.action", {"tid": tid}, function (data) {
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
<div class="content">
    <form class="desc_editor" id="desc_editor" onsubmit="return false">
        <p class="main_title rpg_font rpg_5h">用户详情编辑</p>
        <ul class="icon_selector" id="icon_selector">
            <c:if test="${my_desc.sex==true}">
                <c:forEach items="${icon_list}" var="l">
                    <li id="p${l}">
                        <img src="<%=base%>/images/character/female/female${l.toString()}.png">
                    </li>
                </c:forEach>
            </c:if>
            <c:if test="${my_desc.sex==false}">
                <c:forEach items="${icon_list}" var="l">
                    <li id="p${l}">
                        <img src="<%=base%>/images/character/male/male${l.toString()}.png">
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
                       autofocus value="${my_desc.nickName}">
            </li>
            <li>
                <label class="rpg_font rpg_3h" for="signature">个性签名</label>
                <textarea class="rpg_font rpg_3h rpg_font" type="text" id="signature" name="signature" required
                          autofocus>${my_desc.signature}</textarea>
            </li>

        </ul>
        <input id="uid" name="uid" type="hidden" value="${my_desc.uid}">
        <input id="iconId" name="iconId" type="hidden" value="0">
    </form>
    <button class="rpg_4h rpg_font save_button" onclick="updateUser()">保存</button>
    <div class="user_tb_info">
        <div class="cell">
            <p class="cell_title rpg_font rpg_3h">我发布的任务</p>
            <ul>
                <c:forEach items="${my_p_tasks}" var="t">
                    <li>
                        <img src="<%=base%>/images/task/task_icon${t.taskIcon}.png">
                        <p class="task_title rpg_font rpg_4h" title="${t.taskDesc}">${t.taskTitle}</p>
                        <div class="bt_group">
                            <c:if test="${t.verifyState == true}">
                                <button title="删除任务" class="smallBtn rpg_font rpg_2h red"
                                        onclick="deleteTask(${t.taskId})">×
                                </button>
                                <c:if test="${t.taskState == 1}">
                                    <button title="通过任务" class="smallBtn rpg_font rpg_2h green"
                                            onclick="completeTask(${t.taskId})">√
                                    </button>
                                </c:if>
                            </c:if>
                            <c:if test="${t.verifyState == false}">
                                <p class="hint rpg_font rpg_2h">尚未通过审核</p>
                            </c:if>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="cell">
            <p class="cell_title rpg_font rpg_3h">进行中的任务</p>
            <ul>
                <c:forEach items="${my_tasks}" var="t">
                    <li>
                        <img src="<%=base%>/images/task/task_icon${t.taskIcon}.png">
                        <p class="task_title rpg_font rpg_4h" title="${t.taskDesc}">${t.taskTitle}</p>
                        <img class="coin" src="<%=base%>/images/common_ui/coin.png">
                        <p class="task_award rpg_font rpg_3h">${t.awardCoin}</p>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="cell">
            <p class="cell_title rpg_font rpg_3h">我发布的博客</p>
            <ul>
                <c:forEach items="${my_blogs}" var="b">
                    <li>
                        <a class="blog_title rpg_font rpg_4h"
                           href="<%=base%>/blog/${b.blogId}">${b.blogTitle}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/footer.jsp" %>