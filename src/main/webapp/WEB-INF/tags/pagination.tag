<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="pagination" required="true" type="org.example.blog.model.Pagination"%>
<c:if test="${pagination != null}">
    <ul class="pagination text-center" role="navigation" aria-label="Pagination">
        <li class="pagination-previous ${pagination.activePreviousPage ? '' : 'disabled'}">
            <c:choose>
                <c:when test="${pagination.activePreviousPage}">
                    <a href="${pagination.previousUrl}" aria-label="Previous page">Previous</a>
                </c:when>
                <c:otherwise>Previous</c:otherwise>
            </c:choose>
        </li>
        <c:forEach items="${pagination.pages}" var="page">
            <c:choose>
                <c:when test="${((page.pageUrl == null) && (page.captionOfNumberPage != null))}">
                    <li class="current">${page.captionOfNumberPage}</li>
                </c:when>
                <c:when test="${((page.pageUrl == null) && (page.captionOfNumberPage == null))}">
                    <li class="ellipsis"></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${page.pageUrl}" aria-label="Page ${page.captionOfNumberPage}">${page.captionOfNumberPage}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <li class="pagination-next ${pagination.activeNextPage ? '' : 'disabled'}}">
            <c:choose>
                <c:when test="${pagination.activeNextPage}">
                    <a href="${pagination.nextUrl}" aria-label="Next page">Next</a>
                </c:when>
                <c:otherwise>Next</c:otherwise>
            </c:choose>
        </li>
    </ul>
</c:if>
