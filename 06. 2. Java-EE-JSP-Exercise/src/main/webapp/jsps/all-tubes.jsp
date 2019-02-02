<%@ page import="metube.domain.models.view.TubeAllViewModel" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<main>
    <div class="container">
        <div class="jumbotron text-center mb-0">
            <h1>All Tubes</h1>
            <hr/>
            <h4>Check our tubes below.</h4>
            <hr/>
            <div>
                <% List<TubeAllViewModel> tubes = (List<TubeAllViewModel>) request.getSession().getAttribute("tubes"); %>
                <%if (tubes.size() == 0) { %>
                <h3>No tubes – <a href="/tubes/create">Create some!</a></h3>
                <%} else {%>
                <div class="row">
                    <div class="mx-auto">
                        <ul>
                            <% for (TubeAllViewModel currentTube : tubes) {%>
                            <li class="text-left"><a
                                    href="/tubes/details?tubeName=<%=currentTube.getName()%>"><%=currentTube.getName() %>
                            </a></li>
                            <% } %>
                        </ul>
                    </div>
                </div>
                <%}%>
            </div>
            <hr/>
            <div class="row mt-4">
                <div class="col col-md-12 text-center">
                    <a class="btn btn-warning" href="/">Back to Home</a>
                </div>
            </div>
        </div>
    </div>
</main>


<footer>
    <c:import url="templates/footer.jsp"/>
</footer>

</body>
</html>
