package org.example.blog.controller.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/contact")
public class ContactControllerServlet extends HttpServlet {
    public static final Logger LOGGER = LoggerFactory.getLogger(ContactControllerServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("dynamicPage", "pages/contact.jsp");
        req.getRequestDispatcher("WEB-INF/jsp/page_template.jsp").forward(req, resp);
    }
}
