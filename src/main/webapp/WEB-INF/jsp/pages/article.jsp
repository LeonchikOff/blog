<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>

<div class="article thumbnail">
    <c:set var="category" value="${applicationScope.CATEGORY_MAP[requestScope.article.idCategory]}"/>
    <img src="${requestScope.article.urlLogo}" alt="${requestScope.article.title}">
    <%---------------------------Article content-----------------------------%>
    <div class="data">
        <%--------------------------Article fields----------------------%>
        <h3>${requestScope.article.title}</h3>
            <ul class="vertical large-horizontal menu">
                <li><i class="fi-folder"></i><a href="/news${category.url}">${category.name}</a></li>
                <li><i class="fi-comments"></i><fmt:formatNumber value="${requestScope.article.countOfComments}"/> comments</li>
                <li><i class="fi-clock"></i>
                    <fmt:formatDate value="${requestScope.article.dateOfCreated}" dateStyle="FULL" timeStyle="SHORT" type="both"/>
                </li>
                <li><i class="fi-eye"></i>Hits: <fmt:formatNumber value="${requestScope.article.countOfViews}"/></li>
            </ul>
        <hr/>
        <%---------------------------Content-----------------------------%>
        <div class="content">
            ${requestScope.article.content}
        </div>
        <%---------------------------Social button------------------------%>
        <div class="row columns social">
            <img src="https://via.placeholder.com/32x32?text=f" alt="social"/>
            <img src="https://via.placeholder.com/32x32?text=t" alt="social"/>
            <img src="https://via.placeholder.com/32x32?text=g" alt="social"/>
            <img src="https://via.placeholder.com/32x32?text=f" alt="social"/>
            <img src="https://via.placeholder.com/32x32?text=t" alt="social"/>
            <img src="https://via.placeholder.com/32x32?text=g" alt="social"/>
        </div>
        <br>
        <%----------------------------Comments section---------------------%>
        <div class="comments">
            <jsp:include page="../fragments/new_comment.jsp"/>
            <div id="comments-list-container">
                <jsp:include page="../fragments/comments.jsp"/>
            </div>

            <div id="comments-load-more-ctrl" class="row column text-center">
                <a class="button hollow expanded load-more-btn"
                ${requestScope.article.countOfComments > fn:length(requestScope.comments) ? '' : 'style="display:none"'}>Load More</a>
            </div>
        </div>
    </div>
</div>


