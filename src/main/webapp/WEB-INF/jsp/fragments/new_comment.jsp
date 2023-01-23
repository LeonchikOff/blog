<%@page pageEncoding="UTF-8" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" %>

<div id="new-comment-container" class="media-object comment-item new-comment">
    <div class="media-object-section">
        <img class="avatar" src="https://via.placeholder.com/80x80" alt="anonym">
    </div>
    <div class="media-object-section" style="width: 100%;">
        <label for="comment-content"></label>
        <textarea id="comment-content" name="comment-content" placeholder="Type a new comment"></textarea>
        <span class="form-error">Comment is required. </span>
        <a href="#" class="float-right button" style="margin:7px 0 0;">Submit</a>
    </div>
</div>