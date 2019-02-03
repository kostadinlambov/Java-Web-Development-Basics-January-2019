package metube.web.servlets;

import metube.domain.models.view.TubeAllViewModel;
import metube.services.TubeService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/tubes/all")
public class AllTubesServlet extends HttpServlet {

    private final TubeService tubeService;

    @Inject
    public AllTubesServlet(TubeService tubeService) {
        this.tubeService = tubeService;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TubeAllViewModel> allTubes = this.tubeService.getAll();

        req.setAttribute("tubes", allTubes);

        req.getRequestDispatcher("/jsps/all-tubes.jsp").forward(req, resp);
    }
}
