package org.example.blog.dao;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.example.blog.dao.mapper.ArticleMapper;
import org.example.blog.dao.mapper.CategoryMapper;
import org.example.blog.dao.mapper.ListMapper;
import org.example.blog.entity.Article;
import org.example.blog.entity.Category;

import java.sql.Connection;
import java.sql.ResultSet;
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
        return queryRunner.query(connection,
                "SELECT count(*) FROM article art",
                new ScalarHandler<Number>())
                .intValue();
    }

    public List<Article> listArticlesByCategory(Connection connection, String categoryUrl, int offset, int limit) throws SQLException {
        return queryRunner.query(connection,
                "SELECT art.* FROM article art, category ctr " +
                        "WHERE art.id_category = ctr.id AND ctr.url = ? ORDER BY art.id DESC OFFSET ? LIMIT ?",
                new ListMapper<>(new ArticleMapper()), categoryUrl, offset, limit);
    }

    public int countArticlesByCategory(Connection connection, String categoryUrl) throws SQLException {
        return queryRunner.query(connection,
                "SELECT count(art.id) FROM article art, category ctr " +
                        "WHERE ctr.id = art.id_category AND ctr.url = ? ",
                new ScalarHandler<Number>(), categoryUrl)
                .intValue();
    }

    public List<Article> listArticlesBySearchQuery(Connection connection, String searchQuery, int offset, int limit) throws SQLException {
        String queryParam = "%" + searchQuery + "%";
        return queryRunner.query(connection,
                "SELECT * FROM article art WHERE (art.title ILIKE ? OR art.content ILIKE ?) ORDER BY art.id DESC OFFSET ? LIMIT ? ",
                new ListMapper<>(new ArticleMapper()), queryParam, queryParam, offset, limit);
    }

    public Integer countArticlesBySearchQuery(Connection connection, String searchQuery) throws SQLException {
        String queryParam = "%" + searchQuery + "%";
        return queryRunner.query(connection,
                "SELECT count(art.id) FROM article art WHERE (art.title ILIKE ? OR art.content ILIKE ?)",
                new ScalarHandler<Number>(), queryParam, queryParam).intValue();
    }

    public Category findCategoryByUrl(Connection connection, String categoryUrl) throws SQLException {
        return queryRunner.query(connection,
                "SELECT * FROM category ctr WHERE ctr.url= ? ",
                new BeanHandler<Category>(Category.class) {
                    @Override
                    public Category handle(ResultSet rs) throws SQLException {
                        Category category = null;
                        if (rs.next()) {
                            category = new BasicRowProcessor().toBean(rs, Category.class);
                            category.setCountOfArticles(rs.getInt("count_of_articles"));
                        }
                        return category;
                    }
                },
                categoryUrl);
    }

}