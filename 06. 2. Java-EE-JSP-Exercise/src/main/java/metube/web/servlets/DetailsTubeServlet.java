package metube.web.servlets;

import metube.domain.models.view.TubeDetailsViewModel;
import metube.services.TubeService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/tubes/details")
public class DetailsTubeServlet extends HttpServlet {

    private final TubeService tubeService;

    @Inject
    public DetailsTubeServlet(TubeService tubeService) {
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = URLDecoder.decode(req.getQueryString().split("=")[1], "UTF-8");

        TubeDetailsViewModel tubeDetailsViewModel = this.tubeService.getByName(name);

        req.setAttribute("tubeDetailsViewModel", tubeDetailsViewModel);
        req.getRequestDispatcher("/jsps/details-tube.jsp").forward(req, resp);
    }
}
