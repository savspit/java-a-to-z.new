<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>

<form action="${pageContext.servletContext.contextPath}/roleUpdate" method="POST" >
    Name : <input type="text" name="name" value="${role.name}"/> <br/>
    <br/>
    <input type='submit' value='update' name='update' >
</form>

</body>
</html>
