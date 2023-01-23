<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" %>
<ul id="mobile-category-menu" class="categories dropdown menu align-right hide-for-large" data-dropdown-menu>
    <li>
        <a href="javascript:void(0);">Categories</a>
        <ul class="menu" style="display:none;">
            <c:forEach items="${applicationScope.CATEGORY_MAP}" var="categoryEntry">
                <c:set value="${categoryEntry.value}" var="categoryValue"/>
                <li class="item">
                    <a href="/news${categoryValue.url}">${categoryValue.name} <span>(${categoryValue.count_of_articles})</span></a>
                </li>
            </c:forEach>
        </ul>
    </li>
</ul>