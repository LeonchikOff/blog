package org.example.blog.entity;

public class Category extends AbstractEntity<Integer> {
    private String name;
    private String url;
    private int countOfArticles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCountOfArticles() {
        return countOfArticles;
    }

    public void setCountOfArticles(int countOfArticles) {
        this.countOfArticles = countOfArticles;
    }
}
