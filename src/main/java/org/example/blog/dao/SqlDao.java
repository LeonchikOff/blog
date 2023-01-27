package org.example.blog.dao;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.example.blog.dao.mapper.ArticleMapper;
import org.example.blog.dao.mapper.CategoryMapper;
import org.example.blog.dao.mapper.ListMapper;
import org.example.blog.entity.Account;
import org.example.blog.entity.Article;
import org.example.blog.entity.Category;
import org.example.blog.entity.Comment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class SqlDao {
    private final QueryRunner queryRunner = new QueryRunner();

    public Map<Integer, Category> getMapWithCategories(Connection connection) throws SQLException {
        return queryRunner.query(connection, "SELECT * FROM category", new CategoryMapper());
    }

    public Article findArticleById(Connection connection, long idArticle) throws SQLException {
        return queryRunner.query(connection,
                "SELECT * FROM article art WHERE art.id = ?",
                new BeanHandler<Article>(Article.class) {
                    @Override
                    public Article handle(ResultSet rs) throws SQLException {
                        Article article = null;
                        if (rs.next()) {
                            article = new BasicRowProcessor().toBean(rs, Article.class);
                            article.setDateOfCreated(rs.getTimestamp("date_of_created"));
                            article.setCountOfViews(rs.getLong("count_of_views"));
                            article.setCountOfComments(rs.getInt("count_of_comments"));
                            article.setUrlArticle(rs.getString("url_article"));
                            article.setUrlLogo(rs.getString("url_logo"));
                            article.setIdCategory(rs.getInt("id_category"));
                        }
                        return article;
                    }
                },
                idArticle);
    }

    public void updateArticleCountOfViews(Connection connection, Article article) throws SQLException {
        queryRunner.update(connection, "UPDATE article SET count_of_views = ? WHERE id = ?", article.getCountOfViews(), article.getId());
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

    public List<Comment> listComments(Connection connection, Long idArticle, int offset, int limit) throws SQLException {
        return queryRunner.query(connection,
                "SELECT com.id, content, id_account, id_article, " +
                        "   com.date_of_created AS comment_date_of_created, " +
                        "   ac.name, email, avatar, " +
                        "   ac.date_of_created  AS account_date_of_created " +
                        "FROM comment com, " +
                        "     account ac " +
                        "WHERE com.id_account = ac.id " +
                        "  AND com.id_article = ? " +
                        "ORDER BY com.id DESC " +
                        "OFFSET ? LIMIT ? ",
                new BeanListHandler<Comment>(Comment.class) {
                    @Override
                    public List<Comment> handle(ResultSet rs) throws SQLException {
                        List<Comment> listComments = new ArrayList<>();
                        while (rs.next()) {
                            Account account = new BasicRowProcessor().toBean(rs, Account.class);
                            account.setId(rs.getLong("id_account"));
                            account.setUrlAvatar(rs.getString("avatar"));
                            account.setDateOfCreated(rs.getTimestamp("account_date_of_created"));
                            Comment comment = new BasicRowProcessor().toBean(rs, Comment.class);
                            comment.setDateOfCreated(rs.getTimestamp("comment_date_of_created"));
                            comment.setIdArticle(rs.getLong("id_article"));
                            comment.setAccount(account);
                            listComments.add(comment);
                        }
                        return listComments;
                    }
                }, idArticle, offset, limit);
    }

}