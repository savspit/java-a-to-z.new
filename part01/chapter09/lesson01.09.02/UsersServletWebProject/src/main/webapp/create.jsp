<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<form action="<%=request.getContextPath()%>/echo/create" method="POST">
    Login : <input type="text" name="login"/> <br/>
    Name : <input type="text" name="name"/> <br/>
    Email : <input type="email" name="email"/> <br/>
    <br/>
    <input type="submit" value="add">
</form>
</body>
</html>
