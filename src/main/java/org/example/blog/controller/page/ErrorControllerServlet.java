package org.example.blog.controller.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/error", "/404"})
public class ErrorControllerServlet extends HttpServlet {
    public static final Logger LOGGER = LoggerFactory.getLogger(ErrorControllerServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean is404 = "/404".equals(req.getRequestURI());
        req.setAttribute("is404", is404);
        req.setAttribute("url", req.getParameter("url"));

        req.setAttribute("dynamicPage", "pages/error.jsp");
        req.getRequestDispatcher("/WEB-INF/jsp/template.jsp").forward(req, resp);
    }
}
