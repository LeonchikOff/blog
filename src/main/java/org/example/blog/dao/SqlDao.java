package org.example.blog.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.example.blog.dao.mapper.CategoryMapper;
import org.example.blog.entity.Category;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public final class SqlDao {
    private final QueryRunner queryRunner = new QueryRunner();

    public Map<Integer, Category> getMapWithCategories(Connection connection) throws SQLException {
        return queryRunner.query(connection, "SELECT * FROM category", new CategoryMapper());
    }
}
