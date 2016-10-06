<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>

<form action="${pageContext.servletContext.contextPath}/roleGet" method="POST" >
    Login : <c:out value="${user.login}"></c:out>
    <br/>
    <c:forEach items="${roles}" var="role">
        <input type="radio" name="role" value="${role.name}" > "${role}"
        <br/>
    </c:forEach>
    <br/>
    <input type='submit' value='select' name='select' />
    <input type='submit' value='edit' name='edit' />
    <input type='submit' value='delete' name='delete' />
</form>

<td><p><a href="${pageContext.servletContext.contextPath}/roleCreate" > Add new role</a></p></td>

</body>
</html>
