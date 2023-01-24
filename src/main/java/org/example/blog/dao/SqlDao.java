package org.example.blog.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.example.blog.dao.mapper.ArticleMapper;
import org.example.blog.dao.mapper.CategoryMapper;
import org.example.blog.dao.mapper.ListMapper;
import org.example.blog.entity.Article;
import org.example.blog.entity.Category;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public final class SqlDao {
    private final QueryRunner queryRunner = new QueryRunner();

    public Map<Integer, Category> getMapWithCategories(Connection connection) throws SQLException {
        return queryRunner.query(connection, "SELECT * FROM category", new CategoryMapper());
    }

    public List<Article> listArticles(Connection connection, int offset, int limit) throws SQLException {
        return queryRunner.query(connection,
                "SELECT * FROM article art ORDER BY art.id DESC OFFSET ? LIMIT ?",
                new ListMapper<>(new ArticleMapper()), offset, limit);
    }

    public int countArticles(Connection connection) throws SQLException {
        return queryRunner.query(connection, "SELECT count(*) FROM article art", new ScalarHandler<Number>()).intValue();
    }
}
