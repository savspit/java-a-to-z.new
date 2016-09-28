<%@ page import="shestakov.models.User" %>
<%@ page import="shestakov.postgresql.DBUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<%User selectedUser = new User();
    if (session.getAttribute("login") != null) {
        selectedUser = DBUtils.getInstance().getUserByLogin((String) session.getAttribute("login"));
        session.removeAttribute("login");
    }
%>
<form action="<%=request.getContextPath()%>/echo/update" method="POST" >
    Login : <input type="text" name="login" value="<%=selectedUser.getLogin()%>"/> <br/>
    Name : <input type="text" name="name" value="<%=selectedUser.getName()%>"/> <br/>
    Email : <input type="email" name="email" value="<%=selectedUser.getEmail()%>"/> <br/>
    <br/>
    <input type='submit' value='update' name='update' >
</form>

</body>
</html>
