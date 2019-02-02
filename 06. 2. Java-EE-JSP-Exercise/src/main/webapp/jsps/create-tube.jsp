<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<main>
    <div class="jumbotron">
        <form action="/tubes/create" method="post">
            <div class="row">
                <div class="col col-md-12 text-center">
                    <h1>Create Tube!</h1>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="mx-auto">
                    <div>
                        <label for="title">Title:</label>
                    </div>
                    <div>
                        <input type="text" id="title" name="title" required>
                    </div>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="mx-auto">
                    <div>
                        <label for="description">Description:</label>
                    </div>
                    <div>
                        <textarea id="description" name="description" cols="22" rows="4" required></textarea>
                    </div>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="mx-auto">
                    <div>
                        <label for="youTubeLink">YouTube Link:</label>
                    </div>
                    <div>
                        <input type="text" id="youTubeLink" name="youTubeLink" required>
                    </div>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="mx-auto">
                    <div>
                        <label for="uploader">Uploader:</label>
                    </div>
                    <div>
                        <input type="text" id="uploader" name="uploader" required>
                    </div>
                </div>
            </div>
            <hr/>
            <div class="row mt-4">
                <div class="col col-md-12 text-center">
                    <button type="submit" class="btn btn-primary">Create Tube</button>
                </div>
            </div>
        </form>
        <hr/>

        <div class="row mt-4">
            <div class="col col-md-12 text-center">
                <a class="btn btn-warning" href="/">Back to Home</a>
            </div>
        </div>
    </div>


    <footer>
        <c:import url="templates/footer.jsp"/>
    </footer>
</main>
</body>
</html>
