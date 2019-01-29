<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="bean" type="com.levelp.example.web.IndexPageBean" scope="request" />
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Hello, JSP!</h1>

    <fmt:formatDate value="${bean.currentDate}" pattern="yyyy MM dd" />

    <table>
        <tbody>
        <c:forEach var="user" items="${bean.users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.login}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <p>
        <a href="add-user">Add user...</a>
    </p>
</body>
</html>
