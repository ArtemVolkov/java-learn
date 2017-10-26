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
    <title>Добавить пользователя</title>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/views/clinic/css/style.css">
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/views/clinic/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/views/clinic/js/script.js"></script>
</head>
<body>
<header>
    <div class="header-bg">
        <img src="${pageContext.servletContext.contextPath}/views/clinic/css/images/head.jpg" width="100%" height="205px" alt="Как поймать льва в пустыне">
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
<form id="userform" action="${pageContext.servletContext.contextPath}/create" method="post"
      onsubmit="return validateUserForm()">
    Name: <input type="text" name="name" id="name">
    <br>
    <input type="submit" value="Create user!">
</form>
</body>
</html>
