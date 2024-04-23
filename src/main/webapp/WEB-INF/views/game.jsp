<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="utils.UrlUtils" %>
<%@ page import="model.Player" %>
<%@ page import="model.Game" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
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

<div class="container">
    <div class="mt-5 row justify-content-between">
        <div class=" col-2 mt-3">
            <h5>${game.getGameID()}</h5>
        </div>
        <div class="justify-content-center  col-7">
            <h1 class="text-center text-primary mb-5">GAME ĐOÁN SỐ</h1>
            <form class="container col-md-6 ${game.isComplete() ? "d-none" : ""} " method="post"
                  action="<%=request.getContextPath() + UrlUtils.GAME + "?gameID=" + ((Game) request.getAttribute("game")).getGameID() %>">
                <div class="mb-3">
                    <input required type="text" name="guessNumber" class="form-control">
                </div>
                <div class="d-grid gap-2">
                    <button type="submit" class="btn btn-primary">Guess</button>
                </div>
            </form>
            <h3 class="text-center text-success ${game.isComplete() ? "" : "d-none"}">Bingo !!! Bingo !!! Bingo !!!</h3>
            <div class="container col-xxl-12 mt-3">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col" class="col-sm-4">Time</th>
                        <th scope="col">Number</th>
                        <th scope="col">Result</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="guess" items="${game.getGuessListReversed()}" varStatus="loop">
                        <c:choose>
                            <c:when test="${guess.getGuessResult() == -1}">
                                <tr class="table-danger">
                                    <td>${guess.getGuessTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))}</td>
                                    <td>${guess.getGuessNumber()}</td>
                                    <td>Your number is lower than target</td>
                                </tr>
                            </c:when>
                            <c:when test="${guess.getGuessResult() == 1}">
                                <tr class="table-warning">
                                    <td>${guess.getGuessTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))}</td>
                                    <td>${guess.getGuessNumber()}</td>
                                    <td>Your number is higher than target</td>
                                </tr>
                            </c:when>
                            <c:when test="${guess.getGuessResult() == 0}">
                                <tr class="table-success">
                                    <td>${guess.getGuessTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))}</td>
                                    <td>${guess.getGuessNumber()}</td>
                                    <td>Correct !!!</td>
                                </tr>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class=" col-2 mt-3 text-end">
            <form method="post" action="<%=request.getContextPath() + UrlUtils.NEW_GAME%>">
                <div class="d-grid gap-2">
                    <button type="submit" class="btn btn-primary">New Game</button>
                </div>
            </form>
        </div>
    </div>
</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>