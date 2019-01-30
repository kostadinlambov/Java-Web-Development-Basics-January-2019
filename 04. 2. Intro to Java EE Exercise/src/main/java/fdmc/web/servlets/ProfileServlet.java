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
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/cats/profile")
public class ProfileServlet extends HttpServlet {
    private final static String PROFILE_HTML_FILE_PATH = "C:\\Users\\valch\\Desktop\\Java-Web-Projects\\01. Java Web - January 2019\\01. Java-Web-Development-Basics-January 2019\\04. 2. Intro to Java EE Exercise\\src\\main\\resources\\views\\profile.html";

    private final static String STYLE_CSS_FILE_PATH = "C:\\Users\\valch\\Desktop\\Java-Web-Projects\\01. Java Web - January 2019\\01. Java-Web-Development-Basics-January 2019\\04. 2. Intro to Java EE Exercise\\src\\main\\resources\\css\\style.css";

    private final HtmlReader htmlReader;

    @Inject
    public ProfileServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String catName = req.getParameter("catName");

        Map<String, Cat> cats = (Map<String, Cat>) req.getSession().getAttribute("cats");
        Cat currentCat = cats.get(catName);

        String result = this.htmlReader.readHtmlFile(PROFILE_HTML_FILE_PATH);
        String styleCssContent = this.htmlReader.readHtmlFile(STYLE_CSS_FILE_PATH);

        result = result.replace("toReplace", styleCssContent);

        if (currentCat == null) {
            String notFoundOutput = String.format("<h1 class=\"red-text\">Cat, with name - <span class=\"catName\">%s</span> was not found.</h1>", catName);
            result = result.replaceAll("(?:<section>((?:.*?\\r?\\n?)*)<\\/section>)+", notFoundOutput);
        } else {
            result = result
                    .replace("%name", currentCat.getName())
                    .replace("%breed", currentCat.getBreed())
                    .replace("%color", currentCat.getColor())
                    .replace("%age", currentCat.getAge() + "");
        }

        writer.println(result);
    }
}
