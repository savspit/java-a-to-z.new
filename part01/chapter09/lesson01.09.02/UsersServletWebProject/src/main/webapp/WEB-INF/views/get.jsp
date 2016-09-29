<%@ page import="shestakov.models.User" %>
<%@ page import="shestakov.postgresql.DBUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>

<form action="${pageContext.servletContext.contextPath}/" method="POST" >
    <c:forEach items="${users}" var="user">
        <input type="radio" name="user" value="${user.login}" > "${user}"
        <br/>
    </c:forEach>
    <br/>
    <input type='submit' value='edit' name='edit' />
    <input type='submit' value='delete' name='delete' />
</form>

<td><p><a href="${pageContext.servletContext.contextPath}/create" > Add new user</a></p></td>

</body>
</html>
