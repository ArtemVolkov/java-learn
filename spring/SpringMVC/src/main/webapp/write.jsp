<%--
  Created by IntelliJ IDEA.
  User: Артём
  Date: 19.05.2018
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>mvc is working!</title>
</head>
<body>
   <form method="post" action="/write" modelAttribute="page">
       <input type="text" name="name"/>
       <input type="submit"/>

   </form>
</body>
</html>
