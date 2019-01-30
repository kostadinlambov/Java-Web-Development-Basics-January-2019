package web;

import services.ViewsProvider;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/2")
public class HomeServlet extends HttpServlet {
    private final ViewsProvider viewsProvider;

    @Inject
    public HomeServlet(ViewsProvider viewsProvider) {
        this.viewsProvider = viewsProvider;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = viewsProvider.view("home/get");
        resp.getWriter()
                .write(html);
    }
}
