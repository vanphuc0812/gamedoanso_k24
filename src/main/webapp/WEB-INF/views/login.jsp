<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="utils.UrlUtils" %>
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
    <div class="container col-md-4 mt-5">
        <h1 class="text-center text-primary">LOGIN</h1>
        <form method="post" action="<%=request.getContextPath() + UrlUtils.LOGIN%>">
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" name="username" placeholder="Input your username" class="form-control" id="username" aria-describedby="emailHelp">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" name="password" placeholder="Input your password" class="form-control" id="password">
            </div>
            <div class="mb-3"><a href="/register"> Have no account? Register </a></div>

            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
        <div class="alert alert-danger mt-4 <%=request.getAttribute("errors") == null ? "d-none" : "" %>" role="alert">
            <%=request.getAttribute("errors")%>
        </div>
    </div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>