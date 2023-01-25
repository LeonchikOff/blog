<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<table>
    <tbody>
    <tr>
        <th style="width:270px;">Categories</th>
    </tr>
    <c:forEach items="${applicationScope.CATEGORY_MAP}" var="categoryEntry">
        <c:set var="categoryValue" value="${categoryEntry.value}"/>
        <tr>
            <td class="${requestScope.categoryByUrlId == categoryEntry.key ? ' selected ' : ''} item">
                <a href="/news${categoryValue.url}">${categoryValue.name}
                    <span>(${categoryValue.countOfArticles})</span>
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>