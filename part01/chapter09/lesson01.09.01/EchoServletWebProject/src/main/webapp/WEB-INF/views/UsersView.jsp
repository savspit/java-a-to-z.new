<%@ page import="shestakov.servlets.UserStorage" %>
<%@ page import="shestakov.servlets.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>

<form action="${pageContext.servletContext.contextPath}/" method="POST">
    Login : <input type="text" name="login"><br/>
    Email : <input type="text" name="email"><br/>
    <input type="submit">
</form>
<br/>
<table style="border: 1px solid black;" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>login</th>
        <th>email</th>
    </tr>
    <c:forEach items="${users}" var="user">
    <tr>
        <td><a href="${pageContext.servletContext.contextPath}/Items.html?id=${user.id}"><c:out value="${user.login}"></a></c:out></td>
        <td><c:out value="${user.email}"></c:out></td>
    </tr>
    </c:forEach>
</table>

</body>
</html>
