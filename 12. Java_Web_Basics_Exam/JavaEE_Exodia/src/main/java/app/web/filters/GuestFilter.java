package app.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({
        "/faces/jsf/home_2.xhtml",
        "/faces/jsf/details.xhtml",
        "/faces/jsf/print.xhtml",
        "/faces/jsf/schedule.xhtml",
        "/home",
        "/details",
        "/print",
        "/schedule",
})
public class GuestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        if(session.getAttribute("username") == null){
            resp.sendRedirect("/login");
            return;
        }

        chain.doFilter(req, resp);

    }
}
