<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div class="callout alert">
    <c:choose>
        <c:when test="${requestScope.is404 && requestScope.url != null}">
            <h6>Requested resource <strong>${requestScope.url}</strong> not found!</h6>
        </c:when>
        <c:when test="${requestScope.is404}">
            <h6>Requested resource not found!</h6>
        </c:when>
        <c:otherwise>
            <h6>Error, please try again later...</h6>
        </c:otherwise>
    </c:choose>
</div>