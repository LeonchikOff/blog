package org.example.blog.controller.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/article")
public class ArticleControllerServlet extends HttpServlet {
    public static final Logger LOGGER = LoggerFactory.getLogger(ArticleControllerServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("dynamicPage", "pages/article.jsp");
        req.getRequestDispatcher("WEB-INF/jsp/page_template.jsp").forward(req, resp);
    }
}
