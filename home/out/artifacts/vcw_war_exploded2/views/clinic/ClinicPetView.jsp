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
    <link rel="stylesheet" href="views/clinic/css/style.css">
    <meta charset="utf-8" />
</head>
<body>
<header>
    <div class="header-bg">
        <img src="${pageContext.request.contextPath}/views/clinic/css/images/head.jpg" width="100%" height="205px" alt="Как поймать льва в пустыне">
    </div>
</header>
<table align="center">
    <tr>
        <td class="menuItem"><a href="${pageContext.servletContext.contextPath}/main">Главная</a></td>
        <td class="menuItem"><a href="${pageContext.servletContext.contextPath}/create">Создать</a></td>
        <td class="menuItem"><a href="${pageContext.servletContext.contextPath}/view">Посмотреть</a></td>
        <td class="menuItem"><a href="${pageContext.servletContext.contextPath}/contacts">Контакты</a></td>
        <td class="menuItem"><a href="${pageContext.servletContext.contextPath}/login">Войти</a></td>
    </tr>
</table>
<ul>
    <H1>${pet.name}</H1>
    <table>
        <tr>
            <td>Procedure name</td>
            <td>coast</td>
        </tr>
        <c:forEach var="p" items="${proc}" varStatus="status">
            <tr>
                <td>${p.name}</td>
                <td>${p.price}</td>
            </tr>
        </c:forEach>
    </table>

</ul>
</body>
</html>
