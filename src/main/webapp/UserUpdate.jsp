<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User Email</title>
</head>
<body>
    <h2>Update User Email</h2>
    <form action="userupdate" method="post">
        <label for="userid">User ID:</label>
        <input type="text" id="userid" name="userid"><br>
        <label for="email">New Email:</label>
        <input type="text" id="email" name="email"><br>
        <button type="submit">Update Email</button>
    </form>

    <p style="color:green;">
        ${messages.success}
    </p>
</body>
</html>
