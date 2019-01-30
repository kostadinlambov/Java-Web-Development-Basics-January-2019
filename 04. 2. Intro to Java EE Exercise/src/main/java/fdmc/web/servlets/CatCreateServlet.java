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
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/cats/create")
public class CatCreateServlet extends HttpServlet {
    private final static String CAT_CREATE_HTML_FILE_PATH= "C:\\Users\\valch\\Desktop\\Java-Web-Projects\\01. Java Web - January 2019\\01. Java-Web-Development-Basics-January 2019\\04. 2. Intro to Java EE Exercise\\src\\main\\resources\\views\\cat-create.html";

    private final static String CAT_CREATE_ERROR_HTML_FILE_PATH= "C:\\Users\\valch\\Desktop\\Java-Web-Projects\\01. Java Web - January 2019\\01. Java-Web-Development-Basics-January 2019\\04. 2. Intro to Java EE Exercise\\src\\main\\resources\\views\\createCatErrorPage.html";

    private final static String STYLE_CSS_FILE_PATH= "C:\\Users\\valch\\Desktop\\Java-Web-Projects\\01. Java Web - January 2019\\01. Java-Web-Development-Basics-January 2019\\04. 2. Intro to Java EE Exercise\\src\\main\\resources\\css\\style.css";

    private final HtmlReader htmlReader;

    @Inject
    public CatCreateServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String catCreateHTMLContent = this.htmlReader.readHtmlFile(CAT_CREATE_HTML_FILE_PATH);
        String styleCssContent = this.htmlReader.readHtmlFile(STYLE_CSS_FILE_PATH);

        catCreateHTMLContent = catCreateHTMLContent.replace("toReplace", styleCssContent);

        resp.getWriter().println(catCreateHTMLContent);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cat cat = new Cat();

        cat.setName(req.getParameter("name"));
        cat.setBreed(req.getParameter("breed"));
        cat.setColor(req.getParameter("color"));
        cat.setAge(Integer.parseInt(req.getParameter("age")));

        if(req.getSession().getAttribute("cats")== null){
            req.getSession().setAttribute("cats", new LinkedHashMap<>());
        }

        Map<String, Cat> cats = (Map<String, Cat>) req.getSession().getAttribute("cats");

        if(cats.get(cat.getName()) != null){
            String catCreateErrorHTMLContent = this.htmlReader.readHtmlFile(CAT_CREATE_ERROR_HTML_FILE_PATH);
//            String styleCssContent = this.htmlReader.readHtmlFile(STYLE_CSS_FILE_PATH);

            catCreateErrorHTMLContent = catCreateErrorHTMLContent.replace("%name", req.getParameter("name"));
//            catCreateErrorHTMLContent = catCreateErrorHTMLContent.replace("toReplace", styleCssContent);

            resp.getWriter().println(catCreateErrorHTMLContent);
        }else{
            ((Map<String, Cat>)req.getSession().getAttribute("cats")).putIfAbsent(cat.getName(), cat);

            String redirectUrl = String.format("/cats/profile?catName=%s", cat.getName());
            resp.sendRedirect(redirectUrl);
        }
    }
}
