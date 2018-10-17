<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User edit</title>
</head>
<body>
<center>
    <h1>User edit</h1>

    <form action="${pageContext.servletContext.contextPath}/admin/edituser" method="POST">

            <input type="hidden" name="id" value="${user.id}">

            <label for="uName">Username:</label>
            <input type="text" id="uName" class="form-control" name="name" placeholder="Input name" value="${user.name}"/>

            <label for="uLogin">Login:</label>
            <input type="text" id="uLogin" name="login" placeholder="Input login" value="${user.login}"/>

            <label for="uPassword">Password:</label>
            <input type="text" id="uPassword" name="password" placeholder="Input password" value="${user.password}"/>

            <input type="submit" align="center" value="Submit"/>


    </form>





</center>

</body>
</html>