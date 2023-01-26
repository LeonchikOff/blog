package org.example.blog.controller.page;

import org.apache.commons.lang3.StringUtils;
import org.example.blog.Constants;
import org.example.blog.entity.Article;
import org.example.blog.model.Model;
import org.example.blog.model.Pagination;
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
import java.net.URLEncoder;

@WebServlet("/search")
public class SearchControllerServlet extends HttpServlet {
    public static final Logger LOGGER = LoggerFactory.getLogger(SearchControllerServlet.class);

    private BusinessService businessService;

    @Override
    public void init() throws ServletException {
        businessService = ServiceManager.getInstance(this.getServletContext()).getBusinessService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int offset = 0;
        String pageParam = req.getParameter("page");
        if(pageParam != null) {
            int page = Integer.parseInt(pageParam);
            offset = (page - 1) * Constants.LIMIT_ARTICLES_PER_PAGE;
        }
        String searchQuery = req.getParameter("query");
        if(StringUtils.isNotBlank(searchQuery)) {
            Model<Article> articleModel = businessService.listArticlesBySearchQuery(searchQuery, offset, Constants.LIMIT_ARTICLES_PER_PAGE);
            req.setAttribute("articlesList", articleModel.getCurrentDataList());
            req.setAttribute("allCountOfArticlesBySearchQuery", articleModel.getTotalAmountOfData());
            req.setAttribute("searchQuery", searchQuery);
            Pagination pagination = new Pagination.Builder("/search?query=" + URLEncoder.encode(searchQuery, "UTF-8") + "&", offset, articleModel.getTotalAmountOfData())
                    .builderWithCustomLimit(Constants.LIMIT_ARTICLES_PER_PAGE).build();
            req.setAttribute("pagination", pagination);
            req.setAttribute("dynamicPage", "pages/search.jsp");
            req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/news");
        }
    }
}
