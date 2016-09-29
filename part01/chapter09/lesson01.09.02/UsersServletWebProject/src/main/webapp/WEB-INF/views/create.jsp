<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>

<form action="${pageContext.servletContext.contextPath}/create" method="POST">
    Login : <input type="text" name="login"/> <br/>
    Name : <input type="text" name="name"/> <br/>
    Email : <input type="email" name="email"/> <br/>
    <br/>
    <input type="submit" value="add">
</form>
</body>
</html>
