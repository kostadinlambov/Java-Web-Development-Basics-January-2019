
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<main>
    <% Throwable throwable = (Throwable)
            request.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer)
                request.getAttribute("javax.servlet.error.status_code");
        String servletName = (String)
                request.getAttribute("javax.servlet.error.servlet_name");
        String requestUri = (String)
                request.getAttribute("javax.servlet.error.request_uri");
    %>
    <div class="container">
        <div class="jumbotron text-center mb-0">
            <h1 class="text-info">Something went wrong!</h1>
            <hr/>
            <h2 class="text-info">Error information</h2>
            <hr/>
            <h4>Status code : <span class="text-danger"><%=statusCode%></span></h4>
            <hr/>
            <h4>Servlet Name : <span class="text-danger"><%=servletName%></span></h4>
            <hr/>
            <h4>Exception Type : <span class="text-danger"><%=throwable.getClass().getName()%></span></h4>
            <hr/>
            <h4>The request URI: <span class="text-danger"><%=requestUri%></span></h4>
            <hr/>
            <h4>The exception message: <span class="text-danger"><%=throwable.getMessage()%></span></h4>
            <hr/>
            <div class="d-flex justify-content-around">
                <a class="btn btn-primary" href="/tubes/create">Create Tube</a>
                <a class="btn btn-primary" href="/tubes/all">All Tubes</a>
            </div>
        </div>
    </div>
</main>
<footer>
    <c:import url="templates/footer.jsp"/>
</footer>

</body>
</html>
