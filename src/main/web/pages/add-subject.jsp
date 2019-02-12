<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add subject</title>
    <link rel="stylesheet" type="text/css" href="/static/styles/main.css">
</head>
<body>
    <form:form method="post"
            action="/add-subject"
            enctype="application/x-www-form-urlencoded"
            modelAttribute="subject"
    >
        <p>
            <label>
                Кадастровый номер:
                <form:input path="cadNumber" type="text" />
            </label>

            <form:errors path="cadNumber" cssClass="error"></form:errors>
        </p>
        <p>
            <label>
                Заголовок:
                <form:input path="title" type="text" />
            </label>
        </p>
        <p>
            <label>
                Адрес:
                <form:input path="address" type="text" />
            </label>
        </p>

        <p>
            <label>
                Имя владельца:
                <form:input path="name" type="text" />
            </label>

            <form:errors path="name" cssClass="error"></form:errors>
        </p>

        <p>
            <label>
                Паспорт:
                <form:input path="passportNumber" type="text" />
            </label>
        </p>

        <p>
            <input type="submit">
        </p>

        <form:errors cssClass="error" />
    </form:form>
</body>
</html>
