package org.example.blog.controller.ajax;

import org.example.blog.Constants;
import org.example.blog.entity.Comment;
import org.example.blog.service.BusinessService;
import org.example.blog.service.impl.ServiceManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/ajax/comments")
public class MoreCommentsControllerServlet extends HttpServlet {
    private BusinessService businessService;

    @Override
    public void init() throws ServletException {
        businessService = ServiceManager.getInstance(this.getServletContext()).getBusinessService();
    }

    private int getOffset(HttpServletRequest request) {
        String offsetParam = request.getParameter("offset");
        return offsetParam != null ? Integer.parseInt(offsetParam) : 0;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int offset = this.getOffset(req);
        long idArticle = Long.parseLong(req.getParameter("idArticle"));
        List<Comment> comments = businessService.listComments(idArticle, offset, Constants.LIMIT_COMMENTS_PER_PAGE);
        req.setAttribute("comments", comments);
        req.getRequestDispatcher("/WEB-INF/jsp/fragments/comments.jsp").forward(req, resp);
    }
}
