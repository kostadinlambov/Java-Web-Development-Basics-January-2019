//package metube.web.servlets;
//
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Arrays;
//
//import static javax.servlet.RequestDispatcher.*;
//
//@WebServlet("/errorHandler")
//public class ErrorHandlerServlet extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//
//        resp.setContentType("text/html; charset=utf-8");
//
//        Throwable throwable = (Throwable)
//                req.getAttribute("javax.servlet.error.exception");
//        Integer statusCode = (Integer)
//                req.getAttribute("javax.servlet.error.status_code");
//        String servletName = (String)
//                req.getAttribute("javax.servlet.error.servlet_name");
//
//
//
////        try (PrintWriter writer = resp.getWriter()) {
////            writer.write("<html><head><title>Error description</title></head><body>");
////            writer.write("<h2>Error description</h2>");
////            writer.write("<ul>");
////            Arrays.asList(
////                    ERROR_STATUS_CODE,
////                    ERROR_EXCEPTION_TYPE,
////                    ERROR_MESSAGE)
////                    .forEach(e ->
////                            writer.write("<li>" + e + ":" + req.getAttribute(e) + " </li>")
////                    );
////            writer.write("</ul>");
////            writer.write("</html></body>");
////        }
//    }
//}
