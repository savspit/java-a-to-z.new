<%@ page import="shestakov.models.User" %>
<%@ page import="shestakov.postgresql.DBUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>

<form action="${pageContext.servletContext.contextPath}/update" method="POST" >
    Login : <input type="text" name="login" value="${user.login}"/> <br/>
    Name : <input type="text" name="name" value="${user.name}"/> <br/>
    Email : <input type="email" name="email" value="${user.email}"/> <br/>
    <br/>
    <input type='submit' value='update' name='update' >
</form>

</body>
</html>
