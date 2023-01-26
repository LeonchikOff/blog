package org.example.blog.controller.page;

import org.apache.commons.lang3.StringUtils;
import org.example.blog.entity.Article;
import org.example.blog.exception.RedirectToValidUrlException;
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

@WebServlet("/article/*")
public class ArticleControllerServlet extends HttpServlet {
    public static final Logger LOGGER = LoggerFactory.getLogger(ArticleControllerServlet.class);

    private BusinessService businessService;

    @Override
    public void init() throws ServletException {
        businessService = ServiceManager.getInstance(this.getServletContext()).getBusinessService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        String requestURI = req.getRequestURI();
        long idArticle = Long.parseLong(StringUtils.split(requestURI, "/")[1]);
            Article article = businessService.viewArticle(idArticle, requestURI);
            if(article == null) {
                resp.sendRedirect("/404?url=" + requestURI);
            } else {
                req.setAttribute("article", article);
                req.setAttribute("dynamicPage", "pages/article.jsp");
                req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
            }
        } catch (RedirectToValidUrlException e) {
            resp.sendRedirect(e.getUrl());
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException runtimeException) {
            resp.sendRedirect("/news");
        }
    }
}
