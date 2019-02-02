package metube.web.servlets;

import metube.domain.models.service.TubeServiceModel;
import metube.services.TubeService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tubes/create")
public class CreateTubeServlet extends HttpServlet {
    private final TubeService tubeService;

    @Inject
    public CreateTubeServlet(TubeService tubeService) {
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsps/create-tube.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("title");
        String description = req.getParameter("description");
        String youTubeLink = req.getParameter("youTubeLink");
        String uploader = req.getParameter("uploader");

        TubeServiceModel tubeServiceModel = new TubeServiceModel();
        tubeServiceModel.setName(name);
        tubeServiceModel.setDescription(description);
        tubeServiceModel.setYouTubeLink(youTubeLink);
        tubeServiceModel.setUploader(uploader);

        this.tubeService.saveTube(tubeServiceModel);

        resp.sendRedirect("/tube/details?tubeName="+ tubeServiceModel.getName());
    }
}
