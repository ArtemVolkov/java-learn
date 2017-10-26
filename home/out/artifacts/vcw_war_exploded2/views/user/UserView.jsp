<%--
  Created by IntelliJ IDEA.
  User: Артём
  Date: 06.10.2017
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="${pageContext.servletContext.contextPath}/views/user/UserCreate.jsp">Добавить пользователя</a>
<table border="1">
    <tr>
        <td>Логин Email</td>
        <td>Действие</td>
    </tr>
    <c:forEach var="user" items="${users}" varStatus="status">
        <tr valign="top">
            <td>${user.name} ${user.email} ${user.id}</td>
            <td>
                <a href="${pageContext.servletContext.contextPath}/edit?id=${user.id}">Редактировать</a>
                <a href="${pageContext.servletContext.contextPath}/delete?id=${user.id}">Удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
