package org.example.blog.dao.mapper;

import org.example.blog.entity.Article;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleMapper extends AbstractMapper<Article> {
    @Override
    public Article handleItem(ResultSet resultSet) throws SQLException {
        Article article = this.rowProcessor.toBean(resultSet, Article.class);
        article.setDateOfCreated(resultSet.getTimestamp("date_of_created"));
        article.setCountOfViews(resultSet.getLong("count_of_views"));
        article.setCountOfComments(resultSet.getInt("count_of_comments"));
        article.setUrlArticle(resultSet.getString("url_article"));
        article.setUrlLogo(resultSet.getString("url_logo"));
        article.setIdCategory(resultSet.getInt("id_category"));
        return article;
    }
}
