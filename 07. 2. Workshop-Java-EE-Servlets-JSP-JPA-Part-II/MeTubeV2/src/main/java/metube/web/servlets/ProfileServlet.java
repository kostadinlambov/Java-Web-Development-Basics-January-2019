package metube.web.servlets;

import metube.domain.models.view.UserProfileViewModel;
import metube.service.UserService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet  extends HttpServlet {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Inject
    public ProfileServlet(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserProfileViewModel userProfileViewModel = this.modelMapper
                .map(this.userService.findUserByUsername((String) req.getSession().getAttribute("username")), UserProfileViewModel.class);
        req.setAttribute("model", null);
        req.setAttribute("model", userProfileViewModel);

        req.getRequestDispatcher("/jsp/profile.jsp").forward(req, resp);
    }
}
