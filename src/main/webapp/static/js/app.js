$(document).foundation();
$(document).ready(function () {
    $('#mobile-category-menu ul.menu').removeAttr('style');
});

function moreComments() {
    let offset = $('#comments-list-container .comment-item').length;
    let idArticle = $('#comments-list-container').attr('data-id-article');
    $('#comments-load-more-ctrl .load-more-btn').css('display', 'none');
    $('#comments-load-more-ctrl .loading-indicator').css('display', 'block')
    $.ajax({
        url: '/ajax/comments?offset=' + offset + '&idArticle=' + idArticle,
        success: function (data) {
            $('#comments-load-more-ctrl .loading-indicator').css('display', 'none');
            $('#comments-list-container').append(data);
            let actualTotal = $("#comments-list-container .comment-item").length;
            let expectedTotal = $('#comments-list-container').attr('data-comments-count');
            if (actualTotal == expectedTotal) {
                $('#comments-load-more-ctrl .load-more-btn').css('display', 'none');
            } else {
                $('#comments-load-more-ctrl .load-more-btn').css('display', 'block');
            }
        },
        error: function (data) {
            alert(messages.errorAjax);
        }
    });
}