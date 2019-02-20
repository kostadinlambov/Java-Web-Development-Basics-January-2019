package app.web.filters;

import javax.faces.context.FacesContext;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({
        "/faces/jsf/home.xhtml",
        "/faces/jsf/friends.xhtml",
        "/faces/jsf/profile.xhtml",
        "/home",
        "/friends",
        "/profile",
})
public class GuestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        Object id = session.getAttribute("id");

        if(session.getAttribute("id") == null){
            resp.sendRedirect("/login");
            return;
        }

        chain.doFilter(req, resp);

    }
}
