<%@ page pageEncoding="UTF-8" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="small-12 medium-6 large-5 columns">
        <p class="logo"><i class="fi-shield"></i>PERSONAL BLOG</p>
        <p class="footer-links">
            <a href="<c:url value="/news"/>">News</a>
            <a href="#">Search</a>
            <a href="<c:url value="/about"/>">About</a>
            <a href="<c:url value="/contact"/>">Contact</a>
        </p>
        <p class="copywrite">Copywrite not copywrite © 2015</p>
    </div>
    <div class="small-12 medium-6 large-4 columns">
        <ul class="contact">
            <li><p><i class="fi-marker"></i>1234 Spring Street New York, CT 00001</p></li>
            <li><p><i class="fi-telephone"></i><a href="tel:12223334444">+1-222-333-4444</a></p></li>
            <li><p><i class="fi-mail"></i><a href="mailto:contact@example.com">contact@example.com</a></p></li>
        </ul>
    </div>
    <div class="small-12 medium-12 large-3 columns">
        <p class="about">About Blog</p>
        <p class="about subheader">A blog is a discussion or informational site published on the World Wide Web
            consisting of discrete entries ("posts") typically displayed in reverse chronological order. </p>
        <ul class="inline-list social no-bullet">
            <li><a href="#"><i class="fi-social-facebook"></i></a></li>
            <li><a href="#"><i class="fi-social-twitter"></i></a></li>
            <li><a href="#"><i class="fi-social-linkedin"></i></a></li>
            <li><a href="#"><i class="fi-social-github"></i></a></li>
        </ul>
    </div>
</div>