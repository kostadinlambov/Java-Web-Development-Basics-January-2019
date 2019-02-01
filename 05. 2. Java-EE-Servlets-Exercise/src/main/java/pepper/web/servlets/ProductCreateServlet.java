package pepper.web.servlets;

import pepper.domain.entitties.Type;
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
import java.util.Arrays;
import java.util.List;

@WebServlet("/products/create")
public class ProductCreateServlet extends HttpServlet {
    private final static String CREATE_PRODUCT_HTML_FILE_PATH = "C:\\Users\\valch\\Desktop\\Java-Web-Projects\\01. Java Web - January 2019\\01. Java-Web-Development-Basics-January 2019\\05. 2. Java-EE-Servlets-Exercise\\src\\main\\resources\\views\\create-product.html";

    private final static String CREATE_PRODUCT_ERROR_HTML_FILE_PATH = "C:\\Users\\valch\\Desktop\\Java-Web-Projects\\01. Java Web - January 2019\\01. Java-Web-Development-Basics-January 2019\\05. 2. Java-EE-Servlets-Exercise\\src\\main\\resources\\views\\create-product-error.html";

    private final ProductService productService;
    private final HtmlReader htmlReader;


    @Inject
    public ProductCreateServlet(ProductService productService, HtmlReader htmlReader) {
        this.productService = productService;
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String htmlFileContent = this.htmlReader
                .readHtmlFile(CREATE_PRODUCT_HTML_FILE_PATH)
                .replace("{{typeOptions}}", this.formatTypeOptions());

        resp.getWriter().println(htmlFileContent);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<ProductServiceModel> allProducts = this.productService.getAllProducts();

        for (ProductServiceModel product : allProducts) {
            if (product.getName().equals(req.getParameter("name"))) {
                String productCreateErrorHTMLContent = this.htmlReader.readHtmlFile(CREATE_PRODUCT_ERROR_HTML_FILE_PATH);

                productCreateErrorHTMLContent = productCreateErrorHTMLContent.replace("%name",req.getParameter("name") );

                resp.getWriter().println(productCreateErrorHTMLContent);
                return;
            }
        }

        ProductServiceModel productServiceModel = new ProductServiceModel();
        productServiceModel.setName(req.getParameter("name"));
        productServiceModel.setDescription(req.getParameter("description"));
        productServiceModel.setType(req.getParameter("type"));

        this.productService.saveProduct(productServiceModel);

        resp.sendRedirect("/");
    }

    private String formatTypeOptions() {
        StringBuilder options = new StringBuilder();

        Arrays.stream(Type.values()).forEach(type -> {
            options
                    .append(String.format("<option value=\"%s\">%s</option>", type.name(), type.name()))
                    .append(System.lineSeparator());
        });

        return options.toString();
    }
}
