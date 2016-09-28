<%@ page import="shestakov.servlets.UserStorage" %>
<%@ page import="shestakov.servlets.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<form action="<%=request.getContextPath()%>/echo" method="POST">
    Login : <input type="text" name="login"><br/>
    Email : <input type="text" name="email"><br/>
    <input type="submit">
</form>
<br/>
<table style="border: 1px solid black;" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>Login</th>
        <th>login</th>
    </tr>
    <% for (User user : UserStorage.getInstance().getUsers()) { %>
    <tr>
        <td><%=user.getLogin()%></td>
        <td><%=user.getEmail()%></td>
    </tr>
    <% } %>
</table>

</body>
</html>
