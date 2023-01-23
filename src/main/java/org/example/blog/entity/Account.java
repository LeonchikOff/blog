package org.example.blog.entity;

import java.sql.Timestamp;

public class Account extends AbstractEntity<Long> {
    private String name;
    private String email;
    private String urlAvatar;
    private Timestamp dateOfCreated;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public boolean isAvatarExists() {
        return urlAvatar != null;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    public String getDefaultUrlAvatar() {
        return "/static/img/no_avatar.png";
    }

    public Timestamp getDateOfCreated() {
        return dateOfCreated;
    }

    public void setDateOfCreated(Timestamp dateOfCreated) {
        this.dateOfCreated = dateOfCreated;
    }
}
