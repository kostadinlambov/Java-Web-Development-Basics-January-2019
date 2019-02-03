package metube.web.servlets;

import metube.domain.models.binding.TubeCreateBindingModel;
import metube.domain.models.service.TubeServiceModel;
import metube.services.TubeService;
import metube.util.ModelMapper;
import metube.util.ValidationUtil;

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
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Inject
    public CreateTubeServlet(TubeService tubeService, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsps/create-tube.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TubeCreateBindingModel tubeCreateBindingModel = (TubeCreateBindingModel) req.getAttribute("tubeCreateBindingModel");

        if(!this.validationUtil.isValid(tubeCreateBindingModel)){
            throw new IllegalArgumentException("Required fields are missing or incorrect.");
        }

        TubeServiceModel tubeServiceModel = this.modelMapper.map(tubeCreateBindingModel, TubeServiceModel.class);

        try {
            this.tubeService.saveTube(tubeServiceModel);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/tubes/details?tubeName="+ tubeServiceModel.getName());
    }
}
