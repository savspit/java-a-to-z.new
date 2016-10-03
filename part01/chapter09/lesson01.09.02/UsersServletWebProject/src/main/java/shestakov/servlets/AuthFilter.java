package shestakov.servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Auth filter.
 */
public class AuthFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getRequestURI().contains("/singin")) {
            chain.doFilter(req, resp);
        } else {
            HttpSession session = request.getSession();
            if (session.getAttribute("login") == null) {
                ((HttpServletResponse) resp).sendRedirect(String.format("%s/singin", request.getContextPath()));
                return;
            }
            chain.doFilter(req, resp);
        }
    }

    public void destroy() {

    }
}
