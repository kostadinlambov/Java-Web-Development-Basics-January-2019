package metube.web.filters;

import metube.domain.models.binding.TubeCreateBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/tubes/create")
public class TubeCreateFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if(req.getMethod().toLowerCase().equals("post")){
            TubeCreateBindingModel tubeCreateBindingModel = new TubeCreateBindingModel();

            String name = req.getParameter("title");
            String description = req.getParameter("description");
            String youTubeLink = req.getParameter("youTubeLink");
            String uploader = req.getParameter("uploader");

            tubeCreateBindingModel.setName(name);
            tubeCreateBindingModel.setDescription(description);
            tubeCreateBindingModel.setYouTubeLink(youTubeLink);
            tubeCreateBindingModel.setUploader(uploader);

            req.setAttribute("tubeCreateBindingModel", tubeCreateBindingModel);
        }

        chain.doFilter(req, resp);
    }
}
