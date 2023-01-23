package org.example.blog.listener;

import org.example.blog.service.impl.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info("Application context initialized");
        ServiceManager.getInstance(sce.getServletContext());

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServiceManager.getInstance(sce.getServletContext()).destroy();
        LOGGER.info("Application  context destroyed");

    }
}
