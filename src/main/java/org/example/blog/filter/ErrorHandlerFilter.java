package org.example.blog.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "ErrorHandlerFilter", urlPatterns = "/*")
public class ErrorHandlerFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandlerFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String requestURI = httpServletRequest.getRequestURI();
        LOGGER.info(httpServletRequest.getMethod() + ":" + requestURI);
        try {
            chain.doFilter(httpServletRequest, httpServletResponse);
        } catch (Throwable throwable) {
            LOGGER.error("Error during processing the request: " + requestURI, throwable);
//            если ошибка справоцированна AJAX запросом... (фоновым javascript запросом обмена данными браузера с веб-сервером)
            if (requestURI.startsWith("/ajax/")) {
                httpServletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } else if (!requestURI.startsWith("/error")) {
//            если ошибка справоцированна сервером
                httpServletResponse.sendRedirect("/error");
            } else {
//            если ошибка в ErrorHandlerFilter (в этом классе)
                throw new ServletException(throwable);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
