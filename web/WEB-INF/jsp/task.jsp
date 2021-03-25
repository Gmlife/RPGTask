<%@include file="/WEB-INF/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<link href="<%=base%>/css/task.css" type=text/css rel=stylesheet>
<script>
    let path = "";
    if (${cur_page=="task"})
        path = "<%=base%>/task.action"
    else if (${cur_page=="receive"})
        path = "<%=base%>/task/receive.action";

    function showList() {
        let sub = $("#sub")[0].style;
        if (sub.display === "none") {
            sub.display = "block";
        } else if (sub.display === "block") {
            sub.display = "none";
        }
    }

    function changeType() {

        if (${sort=="发布日期"}) {
            location.href = (path + "?sort=award" + "${sd}");
        } else if (${sort=="任务奖励"}) {
            location.href = (path + "?sort=date" + "${sd}");
        }
    }

    function changeSort() {
        if (${sort=="发布日期"}) {
            if (${sd==null})
                location.href = (path + "?sort=date_desc");
            if (${sd=="_desc"})
                location.href = (path + "?sort=date");
        }
        if (${sort=="任务奖励"}) {
            if (${sd==null})
                location.href = (path + "?sort=award_desc");
            if (${sd=="_desc"})
                location.href = (path + "?sort=award");
        }
    }

    function receiveTask(tid) {
        const res = confirm("是否领取该任务？");
        if (res === true) {
            $.post("<%=base%>/task/receive.action", {"r_uid":${my_desc.uid}, "tid": tid}, function (data) {
                if (data === "ok") {
                    alert("领取成功");
                    location.reload();
                } else {
                    alert("领取失败");
                }

            })
        }

    }
</script>
<div class="content">
    <p class="main_title rpg_font rpg_5h">任务列表</p>
    <div class="submenu">
        <form class="search_group" action="<%=base%>/task/search.action" method="post"
              id="task_search">
            <c:if test="${cur_page=='task'}">
                <a class="rpg_3h rpg_font menu_button"
                   href="<%=base%>/task/receive.action?sort=date">只看可领取任务</a>
            </c:if>
            <c:if test="${cur_page=='receive'}">
                <a class="rpg_3h rpg_font menu_button"
                   href="<%=base%>/task.action?sort=date">查看全部任务</a>
            </c:if>
            <label class="rpg_font rpg_3h">搜索</label>
            <input class="search_bar  rpg_font rpg_3h" type="text" id="search_text" name="search_text"
                   placeholder="在此输入要搜索的任务标题" required>
            <img class="search_btn" src="<%=base%>/images/common_ui/search.png"
                 onclick="document.forms['task_search'].submit()">
        </form>
        <div class="sort_group">
            <label class="rpg_font rpg_3h">排序</label>
            <a class="menu_button rpg_font rpg_3h" onclick="changeSort()">${sort}</a>
            <a class="drop_button" onclick="showList()"></a>
            <img class="sort_icon" onclick="changeSort()"
                 src="<%=base%>/images/common_ui/sort${sd}.png">
            <c:if test="${sort=='发布日期'}">
                <a class="sub_button rpg_font rpg_3h" id="sub" onclick="changeType()" style="display: none">任务奖励</a>
            </c:if>
            <c:if test="${sort=='任务奖励'}">
                <a class="sub_button rpg_font rpg_3h" id="sub" onclick="changeType()" style="display: none">发布日期</a>
            </c:if>

        </div>
    </div>
    <div class="task_info_list">
        <c:forEach items="${task_list}" var="t">
            <c:if test="${t.verifyState == true}">
                <div class="task_info">
                    <div class="task_head">
                        <h1 class="rpg_4h rpg_font">${t.taskTitle}</h1>
                        <img class="task_icon" title="查看发布人信息"
                             src="<%=base%>/images/task/task_icon${t.taskIcon}.png"
                             onclick="location.href='<%=base%>/${t.publishUserId}/main.action'">
                    </div>
                    <div class="task_content">
                        <p class="rpg_3h rpg_font">${t.taskDesc}</p>
                    </div>
                    <div class="task_foot">
                        <div class="award">
                            <p class="rpg_3h rpg_font red inline">${t.awardCoin}</p>
                            <img class="inline icon_3h"
                                 src="<%=base%>/images/common_ui/coin.png">
                        </div>
                        <c:if test="${t.publishUserId !=my_desc.uid}">
                            <c:if test="${t.taskState == 0}">
                                <button class="rpg_3h rpg_font submit" onclick="receiveTask(${t.taskId})">领取</button>
                            </c:if>
                            <c:if test="${t.taskState == 1}">
                                <p class="rpg_3h rpg_font progress">进行中</p>
                            </c:if>
                            <c:if test="${t.taskState == 2}">
                                <p class="rpg_3h rpg_font finish">已完成</p>
                            </c:if>
                        </c:if>
                        <c:if test="${t.publishUserId ==my_desc.uid}">
                            <p class="rpg_3h rpg_font finish">我发布</p>
                        </c:if>
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>
</div>


<%@include file="/WEB-INF/footer.jsp" %>