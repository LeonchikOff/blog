<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<ul class="breadcrumbs">
    <c:choose>
        <c:when test="${requestScope.article != null}">
            <c:set var="category" value="${applicationScope.CATEGORY_MAP[requestScope.article.idCategory]}"/>
            <li><a href="<c:url value="/news"/>">News</a></li>
            <li><a href="/news${category.url}">${category.name}</a></li>
            <li>${requestScope.article.shortTitle}</li>
        </c:when>
        <c:when test="${requestScope.selectedCategory != null}">
            <li><a href="<c:url value="/news"/>">News</a></li>
            <li>${requestScope.selectedCategory.name}</li>
        </c:when>
        <c:when test="${requestScope.isNewsPage}">
            <li>News</li>
        </c:when>
        <c:otherwise>
            <li><a href="<c:url value="/news"/>">News</a></li>
        </c:otherwise>
    </c:choose>
</ul>