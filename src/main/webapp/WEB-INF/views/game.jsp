<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="utils.UrlUtils" %>
<%@ page import="model.Player" %>
<!doctype html>
<html lang="en">
<head>
    <title>Example</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<nav class="navbar bg-body-tertiary">
    <div class="container">
        <a class="navbar-brand" href="#">
            <p>Nestech</p>
        </a>
        <div class="dropdown">
            <a class="btn btn-secondary dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                <%=((Player) request.getSession().getAttribute("currentUser")).getUsername()%>
            </a>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="#">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>
    <div class="container col-md-4 mt-5">
        <h1 class="text-center text-primary mb-5">GAME ĐOÁN SỐ</h1>
        <form method="post" action="<%=request.getContextPath() + UrlUtils.LOGIN%>">
            <div class="mb-3">
                <input type="text" name="guessNumber" placeholder="Input your number" class="form-control">
            </div>
            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </form>

    </div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>