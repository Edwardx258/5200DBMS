<%@ page import="sponsor.model.Users" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Read User</title>
</head>
<body>
    <h2>Find User by ID</h2>
    <form action="userread" method="get">
        <label for="userid">User ID:</label>
        <input type="text" id="userid" name="userid"><br>
        <button type="submit">Find User</button>
    </form>

    <c:if test="${user != null}">
        <h3>User Details</h3>
        <p>User ID: ${user.userId}</p>
        <p>Username: ${user.username}</p>
        <p>Email: ${user.email}</p>
        <p>Created At: ${user.createdAt}</p>
    </c:if>

    <p style="color:green;">
        ${messages.success}
    </p>
</body>
</html>
