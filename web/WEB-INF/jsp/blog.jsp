<%@include file="/WEB-INF/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<link href="<%=base%>/css/blog.css" type=text/css rel=stylesheet>
<script>
</script>
<div class="content">
    <p class="main_title rpg_font rpg_5h">博客列表</p>
        <c:forEach items="${blog_list}" var="b">
            <c:if test="${b.verifyState==true}">
            <div class="blog_info">
                <h1 class="blog_t rpg_font rpg_4h">${b.blogTitle}</h1>
                <p class="blog_c rpg_font rpg_4h">${b.blogText}</p>
                <img class="commit_bt" src="<%=base%>/images/common_ui/commit.png">
                <div class="commit_info">
                    <ul>
                        <c:forEach items="${commit_map.get(b.blogId)}" var="c">
                            <li>
                                <p>${c.commitText}${c.commitDate}</p>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            </c:if>
        </c:forEach>
</div>


<%@include file="/WEB-INF/footer.jsp" %>