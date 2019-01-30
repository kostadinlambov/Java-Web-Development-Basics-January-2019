package pepper.web.servlets;

import pepper.domain.models.service.ProductServiceModel;
import pepper.services.ProductService;
import pepper.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products/details")
public class DetailsServlet extends HttpServlet {
    private final static String PRODUCT_DETAILS_HTML_FILE_PATH = "C:\\Users\\valch\\Desktop\\Java-Web-Projects\\01. Java Web - January 2019\\01. Java-Web-Development-Basics-January 2019\\05. 2. Java-EE-Servlets-Exercise\\src\\main\\resources\\views\\deatils-product.html";

    private final HtmlReader htmlReader;
    private final ProductService productService;

    @Inject
    public DetailsServlet(HtmlReader htmlReader, ProductService productService) {
        this.htmlReader = htmlReader;
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName = req.getParameter("productName");
        ProductServiceModel productServiceModel = this.productService.getByName(productName);

        String htmlFileContent = this.htmlReader.readHtmlFile(PRODUCT_DETAILS_HTML_FILE_PATH)
                .replace("{{productName}}", productServiceModel.getName())
                .replace("{{productDescription}}", productServiceModel.getDescription())
                .replace("{{productType}}", productServiceModel.getType().toUpperCase());

        resp.getWriter().println(htmlFileContent);
    }
}
