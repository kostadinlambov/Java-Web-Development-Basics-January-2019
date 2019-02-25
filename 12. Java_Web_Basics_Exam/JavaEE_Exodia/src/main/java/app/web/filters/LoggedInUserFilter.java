package app.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({
        "/faces/jsf/register.xhtml",
        "/faces/jsf/login.xhtml",
        "/faces/jsf/index.xhtml",
        "/register",
        "/login",
        "/index",
        "/"
})
public class LoggedInUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        if (session.getAttribute("username") != null) {
            resp.sendRedirect("/home");
        } else {
            chain.doFilter(req, resp);
        }
    }
}
