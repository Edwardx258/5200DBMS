<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update User</title>
</head>
<body>
    <h1>Update User</h1>

    <form action="UpdateUserServlet" method="post">
        <label for="userId">User ID:</label>
        <input type="text" id="userId" name="userId" required><br><br>

        <label for="userName">Username:</label>
        <input type="text" id="userName" name="userName" required><br><br>

        <label for="userEmail">Email:</label>
        <input type="email" id="userEmail" name="userEmail" required><br><br>

        <label for="userPassword">Password:</label>
        <input type="password" id="userPassword" name="userPassword" required><br><br>

        <button type="submit">Update</button>
    </form>

    <c:if test="${not empty message}">
        <p style="color:green;">${message}</p>
    </c:if>

    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>
</body>
</html>
