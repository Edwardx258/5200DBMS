<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete User</title>
</head>
<body>
    <h2>Delete User</h2>
    <form action="userdelete" method="post">
        <label for="userid">User ID:</label>
        <input type="text" id="userid" name="userid"><br>
        <button type="submit">Delete User</button>
    </form>

    <p style="color:green;">
        ${messages.success}
    </p>
</body>
</html>
