package org.example.blog.service.impl;

import org.apache.commons.dbcp2.BasicDataSource;
import org.example.blog.service.BusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class ServiceManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceManager.class);
    private static final String SERVICE_MANAGER = "SERVICE_MANAGER";
    private final Properties applicationProperties = new Properties();
    private final BusinessService businessService;
    private final BasicDataSource dataSource;

    private ServiceManager(ServletContext servletContext) {
        LOGGER.info("Service Manager created");
        this.loadProperties(applicationProperties);

        dataSource = createBasicDataSource();
        businessService = new BusinessServiceImpl();
    }

    public BasicDataSource getDataSource() {
        return dataSource;
    }

    public BusinessService getBusinessService() {
        return businessService;
    }

    private BasicDataSource createBasicDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(applicationProperties.getProperty("db.driver"));
        basicDataSource.setUrl(applicationProperties.getProperty("db.url"));
        basicDataSource.setUsername(applicationProperties.getProperty("db.username"));
        basicDataSource.setPassword(applicationProperties.getProperty("db.password"));
        basicDataSource.setInitialSize(Integer.parseInt(applicationProperties.getProperty("db.pool.initSize")));
        basicDataSource.setMaxTotal(Integer.parseInt(applicationProperties.getProperty("db.pool.maxSize")));
        basicDataSource.setDefaultAutoCommit(false);
        basicDataSource.setRollbackOnReturn(true);
        return basicDataSource;
    }

    private void loadProperties(Properties properties) {
        try(InputStream inputStream = ServiceManager.class.getClassLoader().getResourceAsStream("application.properties")){
            properties.load(inputStream);
        } catch (IOException ioException) {
            throw new IllegalArgumentException("Can't load properties from classpath application.properties", ioException);
        }
    }

    public static ServiceManager getInstance(ServletContext servletContext) {
        ServiceManager serviceManager = (ServiceManager) servletContext.getAttribute(SERVICE_MANAGER);
        if (serviceManager == null) {
            serviceManager = new ServiceManager(servletContext);
            servletContext.setAttribute("SERVICE_MANAGER", serviceManager);
        }
        return serviceManager;
    }

    public void destroy() {
        try {
            dataSource.close();
        } catch (SQLException sqlException) {
            LOGGER.error("Close data source failed: " + sqlException.getMessage(), sqlException);
        }
        LOGGER.info("Service Manager destroyed");
    }
}
