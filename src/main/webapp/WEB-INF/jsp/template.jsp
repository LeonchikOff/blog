<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Blog template</title>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/app.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/foundation.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/foundation-icons.css"/>">
</head>
<body>
<header>
    <jsp:include page="fragments/header.jsp" />
</header>
<div class="row">
    <nav role="navigation" class="large-12 small-6 medium-8 columns">
        <jsp:include page="fragments/breadcrumbs.jsp"/>
    </nav>
    <div class="small-6 medium-4 columns">
        <jsp:include page="fragments/categories_dropdown.jsp"/>
    </div>
</div>
<section class="row">
    <div id="mainContent" class="large-10 columns" style="min-height:600px;">
        <jsp:include page="${requestScope.dynamicPage}"/>
    </div>
    <div class="columns large-2 show-for-large right" data-sticky-container>
        <div class="sticky categories show-for-large" data-sticky data-anchor="mainContent">
            <jsp:include page="fragments/categories_table.jsp"/>
        </div>
    </div>
</section>
<footer class="footer">
    <jsp:include page="fragments/footer.jsp"/>
</footer>
<script src="<c:url value="/static/js/app.js"/>"></script>
<script src="<c:url value="/static/js/foundation.js"/>"></script>
<script src="<c:url value="/static/js/jquery.js"/>"></script>
<script src="<c:url value="/static/js/what-input.js"/>"></script>
</body>
</html>
