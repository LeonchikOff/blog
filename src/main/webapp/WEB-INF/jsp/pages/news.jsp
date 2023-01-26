<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>

<c:forEach items="${requestScope.articlesList}" var="article">
    <c:set value="${applicationScope.CATEGORY_MAP[article.idCategory]}" var="category"/>
    <div class="article thumbnail">
        <a href="${article.increasedUrlArticle}">
            <img src="${article.urlLogo}" alt="${article.title}">
        </a>
        <div class="data">
            <h3><a href="${article.increasedUrlArticle}">${article.title}</a></h3>
            <ul class="vertical large-horizontal menu">
                <li><i class="fi-folder"></i><a href="/news${category.url}">${category.name}</a></li>
                <li><i class="fi-comments"></i><fmt:formatNumber value="${article.countOfComments}"/> comments</li>
                <li><i class="fi-clock"></i>
                    <fmt:formatDate value="${article.dateOfCreated}" dateStyle="FULL" timeStyle="SHORT" type="both"/>
                </li>
                <li><i class="fi-eye"></i>Hits: <fmt:formatNumber value="${article.countOfViews}"/></li>
            </ul>
            <hr/>
            <div class="desc">
                    ${article.description}
            </div>
        </div>
    </div>
</c:forEach>
<tags:pagination pagination="${requestScope.pagination}"/>
