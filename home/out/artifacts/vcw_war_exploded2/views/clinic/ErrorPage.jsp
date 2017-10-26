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
    <title>Welcome to our clinic</title>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/views/clinic/css/style.css">
</head>
<body>
<div align="center">
    <h1><a href="${pageContext.servletContext.contextPath}/main">Вернуться на главную</a></h1>
    <img src="${pageContext.servletContext.contextPath}/views/clinic/css/images/error.jpg" alt="Ошибка" width="50%">
    <h4>${error}</h4>
</div>




</body>
</html>
