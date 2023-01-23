package org.example.blog.service.impl;

import org.example.blog.dao.SqlDao;
import org.example.blog.entity.Category;
import org.example.blog.exception.UncheckedSystemException;
import org.example.blog.service.BusinessService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

class BusinessServiceImpl implements BusinessService {
    private final DataSource dataSource;
    private final SqlDao sqlDao;

    BusinessServiceImpl(ServiceManager serviceManager) {
        dataSource = serviceManager.getDataSource();
        sqlDao = new SqlDao();
    }

    @Override
    public Map<Integer, Category> getMapWithCategories() {
        try (Connection connection = dataSource.getConnection()) {
            return sqlDao.getMapWithCategories(connection);
        } catch (SQLException sqlException) {
            throw new UncheckedSystemException("Can't execute db command: " + sqlException.getMessage(), sqlException);
        }
    }
}
