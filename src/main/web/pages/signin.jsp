<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form action="/login" method="post">
        <p>
            <label>
                Username:
                <input type="text" name="username" />
            </label>
        </p>
        <p>
            <label>
                Password:
                <input type="password" name="password" />
            </label>
        </p>
        <p>
            <input type="submit" value="Sign in" />
        </p>

        <security:csrfInput/>
    </form>
</body>
</html>
