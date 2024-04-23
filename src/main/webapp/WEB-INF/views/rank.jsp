<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="utils.UrlUtils" %>
<%@ page import="model.Player" %>
<%@ page import="model.Game" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.List" %>
<!doctype html>
<html lang="en">
<head>
    <title>Example</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <h3 class="mb-0">Nestech</h3>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
                aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-link active" aria-current="page" href="#">Home</a>
                <a class="nav-link" href="#">Game</a>
                <a class="nav-link" href="<%=request.getContextPath() + UrlUtils.RANK%>">Rank</a>
            </div>
        </div>
        <div class="dropdown">
            <a class="btn btn-secondary dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
               aria-expanded="false">
                <%=((Player) request.getSession().getAttribute("currentUser")).getUsername()%>
            </a>
            <ul class=" dropdown-menu">
                <li><a class="dropdown-item" href="<%=request.getContextPath() + UrlUtils.LOGOUT%>">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container ">
    <div class="row justify-content-center">
        <h1 class=" text-center text-primary mt-5 mb-5">RANK</h1>
    </div>

    <div class="row justify-content-around">
        <div class="col-5">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Game ID</th>
                    <th scope="col">Username</th>
                    <th scope="col">Target Number</th>
                    <th scope="col">Play Time</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="game" items="${listRank}" varStatus="loop">
                    <tr>
                        <th scope="row">${listRank.indexOf(game) + 1}</th>
                        <td>${game.getGameID()}</td>
                        <td>${game.getUsername()}</td>
                        <td>${game.getTargetNumber()}</td>
                        <td>${game.getGuessTime()}</td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>

        <div class="col-5">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Game ID</th>
                    <th scope="col">Username</th>
                    <th scope="col">Target Number</th>
                    <th scope="col">Guess Times</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="game" items="${listRank}" varStatus="loop">
                    <tr>
                        <th scope="row">${listRank.indexOf(game) + 1}</th>
                        <td>${game.getGameID()}</td>
                        <td>${game.getUsername()}</td>
                        <td>${game.getTargetNumber()}</td>
                        <td>${game.getGuessTime()}</td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>

</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>