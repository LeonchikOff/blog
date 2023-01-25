package org.example.blog.controller.page;

import org.example.blog.Constants;
import org.example.blog.entity.Article;
import org.example.blog.model.Model;
import org.example.blog.service.BusinessService;
import org.example.blog.service.impl.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/news", "/news/*"})
public class NewsControllerServlet extends HttpServlet {
    public static final Logger LOGGER = LoggerFactory.getLogger(ArticleControllerServlet.class);

    private BusinessService businessService;

    @Override
    public void init() {
        businessService = ServiceManager.getInstance(this.getServletContext()).getBusinessService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        Model<Article> articleModel;
        if (requestURI.endsWith("/news") || requestURI.endsWith("/news/")) {
            articleModel = businessService.listArticles(0, Constants.LIMIT_ARTICLES_PER_PAGE);
        } else {
            String categoryURI = requestURI.replace("/news", "");
            articleModel = businessService.listArticlesByCategory(categoryURI, 0, Constants.LIMIT_ARTICLES_PER_PAGE);
            req.setAttribute("categoryByUrlId", businessService.findCategoryByUrl(categoryURI).getId());
        }
        req.setAttribute("articlesList", articleModel.getCurrentDataList());
        req.setAttribute("dynamicPage", "pages/news.jsp");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
    }
}
