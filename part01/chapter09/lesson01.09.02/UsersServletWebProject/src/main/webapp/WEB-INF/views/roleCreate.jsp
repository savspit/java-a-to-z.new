<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>

<form action="${pageContext.servletContext.contextPath}/roleCreate" method="POST">
    Name : <input type="text" name="name"/> <br/>
    <br/>
    <input type="submit" value="add">
</form>
</body>
</html>
