<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>

<form action="${pageContext.servletContext.contextPath}/roleSelect" method="POST" >
    Login : <c:out value="${user.login}"></c:out>
    <br/>
    <input type='submit' value='select' name='select' >
</form>

</body>
</html>
