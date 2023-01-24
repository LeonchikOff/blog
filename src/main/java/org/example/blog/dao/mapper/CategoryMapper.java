package org.example.blog.dao.mapper;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.RowProcessor;
import org.example.blog.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CategoryMapper implements ResultSetHandler<Map<Integer, Category>> {
    private final RowProcessor rowProcessor = new BasicRowProcessor();
    @Override
    public Map<Integer, Category> handle(ResultSet resultSet) throws SQLException {
        Map<Integer, Category> categoryMap = new HashMap<>();
        while (resultSet.next()) {
            Category category = rowProcessor.toBean(resultSet, Category.class);
            category.setCountOfArticles(resultSet.getInt("count_of_articles"));
            categoryMap.put(category.getId(), category);
        }
        return categoryMap;
    }
}
