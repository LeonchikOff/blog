package org.example.blog.controller.page;

import org.apache.commons.lang3.StringUtils;
import org.example.blog.Constants;
import org.example.blog.entity.Article;
import org.example.blog.entity.Comment;
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
import java.util.List;

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
        String requestURI = req.getRequestURI();
        try {
            long idArticle = Long.parseLong(StringUtils.split(requestURI, "/")[1]);
            Article article = businessService.viewArticle(idArticle, requestURI);
            if (article == null) {
                resp.sendRedirect("/404?url=" + requestURI);
            } else {
                req.setAttribute("article", article);
                List<Comment> comments = businessService.listComments(article.getId(), 0, Constants.LIMIT_COMMENTS_PER_PAGE);
                req.setAttribute("comments", comments);
                req.setAttribute("dynamicPage", "pages/article.jsp");
                req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
            }
        } catch (RedirectToValidUrlException e) {
            resp.sendRedirect(e.getUrl());
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException runtimeException) {
            resp.sendRedirect("/404?url=" + requestURI);
        }
    }
}
