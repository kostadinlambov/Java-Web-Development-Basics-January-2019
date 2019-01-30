package web;

import entities.User;
import services.UsersService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/")
public class HomeServlet2 extends HttpServlet {
    private final UsersService usersService;

    @Inject
    public HomeServlet2(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String form = getForm();
        String usersList = getUsersList();

        String html =
                "<html>" +
                        form +
                        usersList +
                        "</html>";

        resp.getWriter()
                .write(html);
    }

    private String getUsersList() {
        return "<ul>" +
                usersService.getAllUsers()
                        .stream()
                        .map(user -> user.getName() + ";" + user.getAge())
                        .map(userText -> "<li>" + userText + "</li>")
                        .collect(Collectors.joining("\n")) +
                "</ul>";
    }

    private String getForm() {
        return "<form action=\"/\" method=\"post\">" +
                "  <label>" +
                "    <input name=\"name\" placeholder=\"Enter your name\" />" +
                "  </label>" +
                "  <label>" +
                "    <input name=\"age\" placeholder=\"Enter your age\" type=\"number\"/>" +
                "  </label>" +
                "  <button>Submit</button>" +
                "</form>";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader()
                .lines()
                .collect(Collectors.joining());

        User user = new User();
        Arrays.stream(body.split("&"))
                .map(pairString -> pairString.split("="))
                .forEach(pair -> {
                    switch (pair[0]) {
                        case "name":
                            user.setName(pair[1]);
                            break;
                        case "age":
                            user.setAge(Integer.parseInt(pair[1]));
                    }
                });

        usersService.add(user);

        resp.sendRedirect("/");
    }
}
