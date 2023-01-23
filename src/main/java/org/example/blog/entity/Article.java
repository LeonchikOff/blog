package org.example.blog.entity;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;

public class Article extends AbstractEntity<Long> {
    private String title;
    private String description;
    private String content;
    private Timestamp dateOfCreated;
    private Long countOfViews;
    private Integer countOfComments;
    private String urlArticle;
    private String urlLogo;
    private Integer idCategory;

    public String getTitle() {
        return title;
    }

    public String getShortTitle() {
        return StringUtils.length(title) > 20
                ? title.substring(0, 17) + "..."
                : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getDateOfCreated() {
        return dateOfCreated;
    }

    public void setDateOfCreated(Timestamp dateOfCreated) {
        this.dateOfCreated = dateOfCreated;
    }

    public Long getCountOfViews() {
        return countOfViews;
    }

    public void setCountOfViews(Long countOfViews) {
        this.countOfViews = countOfViews;
    }

    public Integer getCountOfComments() {
        return countOfComments;
    }

    public void setCountOfComments(Integer countOfComments) {
        this.countOfComments = countOfComments;
    }

    public String getUrlArticle() {
        return urlArticle;
    }

    public String getIncreasedUrlArticle() {
        return "/article/" + this.getId() + urlArticle;
    }

    public void setUrlArticle(String urlArticle) {
        this.urlArticle = urlArticle;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }
}
