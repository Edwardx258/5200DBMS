<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delete User</title>
</head>
<body>
    <h1>Delete User</h1>

    <form action="DeleteUserServlet" method="post">
        <label for="userId">User ID:</label>
        <input type="text" id="userId" name="userId" required><br><br>

        <button type="submit">Delete</button>
    </form>

    <c:if test="${not empty message}">
        <p style="color:green;">${message}</p>
    </c:if>

    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>
</body>
</html>

