<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="utils.UrlUtils" %>
<%@ page import="model.Player" %>
<%@ page import="utils.JspUtils" %>
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
        <a class="navbar-brand" href="<%=request.getContextPath()+UrlUtils.HOME%>">
            <h3 class="mb-0">Nestech</h3>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
                aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-link <%=request.getServletPath().equals(JspUtils.HOME) ? "active" : ""%>"
                   aria-current="page"
                   href="<%=request.getContextPath() + UrlUtils.HOME%>">Home</a>
                <a class="nav-link <%=request.getServletPath().equals(JspUtils.GAME) ? "active" : ""%>"
                   href="<%=request.getContextPath() + UrlUtils.GAME%>">Game</a>
                <a class="nav-link <%=request.getServletPath().equals(JspUtils.RANK) ? "active" : ""%>"
                   href="<%=request.getContextPath() + UrlUtils.RANK%>">Rank</a>
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
    <table class="table table-clickable">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Game ID</th>
            <th scope="col">Start Time</th>
            <th scope="col">End Time</th>
            <th scope="col">Is Complete</th>
            <th scope="col">Is Active</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="game" items="${listGame}" varStatus="loop">
            <tr data-href="<%=request.getContextPath() + UrlUtils.GAME + "/" %>${game.getGameID()}">
                <th scope="row">${listGame.indexOf(game) + 1}</th>
                <td>${game.getGameID()}</td>
                <td>${game.getStartTime()}</td>
                <td>${game.getEndTime()}</td>
                <td>${game.isComplete()}</td>
                <td>${game.isActive()}</td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<script>
    const tableRows = document.querySelectorAll(".table-clickable tbody tr");

    for (const tableRow of tableRows) {
        tableRow.addEventListener("click", function () {
            // window.open(this.dataset.href, "_blank");
            window.location.href = this.dataset.href;
        });
    }
</script>
</body>
</html>