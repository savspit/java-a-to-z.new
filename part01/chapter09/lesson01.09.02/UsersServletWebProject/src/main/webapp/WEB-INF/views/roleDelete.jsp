<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>

<form action="${pageContext.servletContext.contextPath}/roleDelete" method="POST" >
    Login : <c:out value="${user.login}"></c:out>
    <input type='submit' value='delete' name='delete' >
</form>

</body>
</html>
