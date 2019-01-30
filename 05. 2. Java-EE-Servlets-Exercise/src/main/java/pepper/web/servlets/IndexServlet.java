package pepper.web.servlets;

import pepper.domain.models.view.AllProductsViewModel;
import pepper.services.ProductService;
import pepper.util.HtmlReader;
import pepper.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/")
public class IndexServlet extends HttpServlet {
    private final static String INDEX_HTML_FILE_PATH = "C:\\Users\\valch\\Desktop\\Java-Web-Projects\\01. Java Web - January 2019\\01. Java-Web-Development-Basics-January 2019\\05. 2. Java-EE-Servlets-Exercise\\src\\main\\resources\\views\\index.html";

    private final HtmlReader htmlReader;
    private final ProductService productService;
    private final ModelMapper modelMapper;


    @Inject
    public IndexServlet(HtmlReader htmlReader, ProductService productService, ModelMapper modelMapper) {
        this.htmlReader = htmlReader;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String htmlFileContent = this.htmlReader
                .readHtmlFile(INDEX_HTML_FILE_PATH)
                .replace("{{listItems}}", formatListItems());

        resp.getWriter().println(htmlFileContent);
    }

    private String formatListItems(){
        List<AllProductsViewModel> productsViewModels = this.productService.getAllProducts()
                .stream()
                .map(productService -> this.modelMapper
                        .map(productService, AllProductsViewModel.class))
                .collect(Collectors.toList());

        StringBuilder productsSb = new StringBuilder();

        productsViewModels.forEach(product ->
                productsSb.append(String
                        .format("<li><a href=\"/products/details?productName=%s\"><i class=\"fab fa-product-hunt text-info\"></i> %s</a></li>",
                        product.getName(),product.getName())));

       return productsSb.toString();
    }
}
