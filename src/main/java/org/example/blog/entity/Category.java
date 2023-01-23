package org.example.blog.entity;

public class Category extends AbstractEntity<Integer> {
    private String name;
    private String url;
    private int count_of_articles;

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

    public int getCount_of_articles() {
        return count_of_articles;
    }

    public void setCount_of_articles(int count_of_articles) {
        this.count_of_articles = count_of_articles;
    }
}
