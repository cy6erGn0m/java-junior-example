<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:useBean id="bean" type="com.levelp.example.web.IndexPageBean" scope="request" />
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Hello, ${bean.currentUserName}!</h1>

    <fmt:formatDate value="${bean.currentDate}" pattern="yyyy MM dd" />

    <table>
        <tbody>
        <c:forEach var="user" items="${bean.users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.login}</td>
                <td>
                    <form action="/admin/delete-user" method="post">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="submit" value="Delete">
                        <security:csrfInput/>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <p>
        <a href="admin/add-user">Add user...</a>
    </p>
    <p>
        <a href="engineer/add-subject">Add subj...</a>
    </p>
</body>
</html>
