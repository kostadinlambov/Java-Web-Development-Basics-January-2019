<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<div class="container">
    <main>
        <div class="jumbotron text-center">
            <h1>Welcome to MeTube!</h1>
            <hr/>
            <h4>Cool app in beta version</h4>
            <hr/>
            <div class="d-flex justify-content-around">
            <a class="btn btn-primary" href="/tubes/create">Create Tube</a>
            <a class="btn btn-primary" href="/tubes/all">All Tubes</a>
            </div>
        </div>
    </main>
    <footer>
        <c:import url="templates/footer.jsp"/>
    </footer>

</div>

</body>
</html>
