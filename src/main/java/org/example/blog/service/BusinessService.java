package org.example.blog.service;

import org.example.blog.entity.Article;
import org.example.blog.entity.Category;
import org.example.blog.entity.Comment;
import org.example.blog.exception.RedirectToValidUrlException;
import org.example.blog.model.Model;

import java.util.List;
import java.util.Map;

public interface BusinessService {
    Map<Integer, Category> getMapWithCategories();
    Model<Article> listArticles(int offset, int limit);
    Model<Article> listArticlesByCategory(String categoryUrl, int offset, int limit);
    Model<Article> listArticlesBySearchQuery(String searchQuery, int offset, int limit);

    Category findCategoryByUrl(String categoryUrl);
    Article viewArticle(Long idArticle, String requestURL) throws RedirectToValidUrlException;

    List<Comment> listComments(Long idArticle, int offset, int limit);

}
