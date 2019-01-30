package fdmc.web.servlets;

import fdmc.domain.entities.Cat;
import fdmc.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/cats/all")
public class AllCatsServlet extends HttpServlet {
    private final static String CAT_ALL_HTML_FILE_PATH= "C:\\Users\\valch\\Desktop\\Java-Web-Projects\\01. Java Web - January 2019\\01. Java-Web-Development-Basics-January 2019\\04. 2. Intro to Java EE Exercise\\src\\main\\resources\\views\\cat-all.html";

    private final static String STYLE_CSS_FILE_PATH= "C:\\Users\\valch\\Desktop\\Java-Web-Projects\\01. Java Web - January 2019\\01. Java-Web-Development-Basics-January 2019\\04. 2. Intro to Java EE Exercise\\src\\main\\resources\\css\\style.css";

    private final HtmlReader htmlReader;

    @Inject
    public AllCatsServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String catAllHTMLContent = this.htmlReader.readHtmlFile(CAT_ALL_HTML_FILE_PATH);
        String styleCssContent = this.htmlReader.readHtmlFile(STYLE_CSS_FILE_PATH);

        Map<String, Cat> cats = (Map<String, Cat>) req.getSession().getAttribute("cats");

        StringBuilder catsSb = new StringBuilder();

        if(cats == null || cats.isEmpty()){
            catsSb.append("<h2 class=\"red-text\">There are no cats.<a class=\"button\" href=\"/cats/create\">Create some!</a></h2>")
            .append(System.lineSeparator());
        }else{
            for (String currentCatName : cats.keySet()) {
                String currentCatHtmlElement = String.format("<li><a class=\"current-cat\" href=\"/cats/profile?catName=%s\">%s</a></li>", currentCatName, currentCatName);
                catsSb.append(currentCatHtmlElement);
            }
        }

        catAllHTMLContent = catAllHTMLContent.replaceAll("(?:<li>((?:.*?\\r?\\n?)*)<\\/li>)+", catsSb.toString());
        catAllHTMLContent = catAllHTMLContent.replace("toReplace", styleCssContent);

        resp.getWriter().println(catAllHTMLContent);
    }
}
