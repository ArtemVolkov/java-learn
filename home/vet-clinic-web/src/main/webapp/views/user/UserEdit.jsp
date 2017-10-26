<%--
  Created by IntelliJ IDEA.
  User: Артём
  Date: 07.10.2017
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/edit" method="post">
    <input type="hidden" name="id" value="${user.id}">
    <table>
        <tr>
            <td>new Name</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>new Email</td>
            <td><input type="text" name="email"></td>
        </tr>
        <tr>
            <td><input type="submit" value="Confirm" align="center"></td>
        </tr>
    </table>

</form>
</body>
</html>
