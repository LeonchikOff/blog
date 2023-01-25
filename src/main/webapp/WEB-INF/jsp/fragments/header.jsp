<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" %>
<div class="callout primary" style="margin:0;">
    <div class="row">
        <div class="large-6 medium-6 columns">
            <h1>Blog name</h1>
        </div>
        <div class="large-6 medium-6 columns">
            <form action="<c:url value="/search"/>" method="get">
                <div class="input-group" style="margin-top: 1em;">
                    <label for="search">
                        <input id="search" class="input-group-field" name="query" placeholder="Search query" value="${requestScope.searchQuery}" type="text">
                    </label>
                    <div class="input-group-button">
                        <input class="button" value="Find" type="submit">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>