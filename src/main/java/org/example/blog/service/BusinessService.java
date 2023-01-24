package org.example.blog.service;

import org.example.blog.entity.Article;
import org.example.blog.entity.Category;
import org.example.blog.model.Model;

import java.util.Map;

public interface BusinessService {
    Map<Integer, Category> getMapWithCategories();
    Model<Article> listArticles(int offset, int limit);
}
