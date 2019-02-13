package metube.web.servlets;

import metube.domain.models.service.TubeServiceModel;
import metube.domain.models.view.TubeDetailsViewModel;
import metube.service.TubeService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tube/details/*")
public class TubeDetailsServlet extends HttpServlet {

    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public TubeDetailsServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] urlParams = req.getRequestURI().split("/");
        String tubeId = urlParams[urlParams.length - 1];

        TubeServiceModel tubeServiceModel = this.tubeService.findTubeById(tubeId);
        tubeServiceModel.setViews(tubeServiceModel.getViews() + 1);

        TubeDetailsViewModel tubeDetailsViewModel = this.modelMapper.map(tubeServiceModel, TubeDetailsViewModel.class);

        this.tubeService.uploadTube(tubeServiceModel);

        req.setAttribute("model", null);
        req.setAttribute("model", tubeDetailsViewModel);
        req.getRequestDispatcher("/jsp/details.jsp").forward(req, resp);
    }
}
