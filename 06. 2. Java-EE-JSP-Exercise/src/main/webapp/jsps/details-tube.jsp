<%@ page import="metube.services.TubeService" %>
<%@ page import="metube.domain.models.view.TubeDetailsViewModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<div class="container">
    <main>
        <% TubeDetailsViewModel tubeDetailsViewModel = (TubeDetailsViewModel) request.getSession().getAttribute("tubeDetailsViewModel"); %>
        <% String name = request.getParameter("tubeName"); %>
        <div class="jumbotron text-center mb-0">
            <% if (tubeDetailsViewModel == null) {%>
            <h1>Tube with name - <%=name%> does not exist!</h1>
            <%} else {%>
            <h1><%=tubeDetailsViewModel.getName()%>
            </h1>
            <hr/>
            <h4><%=tubeDetailsViewModel.getDescription()%>
            </h4>
            <hr/>
            <div class="d-flex justify-content-around">
                <a target="_blank" rel="noopener noreferrer" href="<%=tubeDetailsViewModel.getYouTubeLink()%>">Link to
                    Video</a>
                <p><%=tubeDetailsViewModel.getUploader()%>
                </p>
            </div>
            <%}%>
            <hr/>
            <div class="row mt-4">
                <div class="col col-md-12 text-center">
                    <a class="btn btn-warning" href="/">Back to Home</a>
                </div>
            </div>
        </div>
    </main>
</div>
<footer>
    <c:import url="templates/footer.jsp"/>
</footer>
</body>
</html>
