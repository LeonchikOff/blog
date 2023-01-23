package org.example.blog.entity;

import java.sql.Timestamp;

public class Comment extends AbstractEntity<Integer> {
    private String content;
    private Timestamp dateOfCreated;
    private Account account;
    private Long idArticle;

    public Comment(String content, Timestamp dateOfCreated, Account account, Long idArticle) {
        this.content = content;
        this.dateOfCreated = dateOfCreated;
        this.account = account;
        this.idArticle = idArticle;
    }

    public Comment() {
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Long idArticle) {
        this.idArticle = idArticle;
    }
}
