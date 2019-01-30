package fdmc.web.servlets;

import fdmc.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class IndexServlet extends HttpServlet {
    private final static String INDEX_HTML_FILE_PATH= "C:\\Users\\valch\\Desktop\\Java-Web-Projects\\01. Java Web - January 2019\\01. Java-Web-Development-Basics-January 2019\\04. 2. Intro to Java EE Exercise\\src\\main\\resources\\views\\index.html";

    private final static String STYLE_CSS_FILE_PATH= "C:\\Users\\valch\\Desktop\\Java-Web-Projects\\01. Java Web - January 2019\\01. Java-Web-Development-Basics-January 2019\\04. 2. Intro to Java EE Exercise\\src\\main\\resources\\css\\style.css";

    private  final HtmlReader htmlReader;

    @Inject
    public IndexServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        String indexHtmlContent = this.htmlReader.readHtmlFile(INDEX_HTML_FILE_PATH);
        String styleCssContent = this.htmlReader.readHtmlFile(STYLE_CSS_FILE_PATH);

        indexHtmlContent = indexHtmlContent.replace("toReplace", styleCssContent);

        writer.println(indexHtmlContent);
    }
}
