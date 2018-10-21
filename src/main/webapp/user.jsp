<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.03.2018
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
</head>
<body>
    <h1>Hello ${user.name}</h1>


    <div align="center">

        <table border="1" cellpadding="5">

            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Login</th>
                <th>Password</th>
                <th>Role</th>
            </tr>

                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.login}</td>
                    <td>${user.password}</td>
                    <td>${user.role}</td>
                </tr>

        </table>

    </div>


</body>
</html>
