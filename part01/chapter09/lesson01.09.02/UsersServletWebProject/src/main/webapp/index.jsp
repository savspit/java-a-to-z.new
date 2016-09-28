<%@ page import="shestakov.models.User" %>
<%@ page import="shestakov.postgresql.DBUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<form action="<%=request.getContextPath()%>/echo/get" method="GET" >
    <%for (User currentUser : DBUtils.getInstance().getAllUsers()) { %>
        <input type="radio" name="user" value="<%=currentUser.getLogin()%>" > <%=currentUser.toString()%>
        <br/>
    <% } %>
    <br/>
    <input type='submit' value='edit' name='edit' />
    <input type='submit' value='delete' name='delete' />
</form>

<td><p><a href="create.jsp" > Add new user</a></p></td>

</body>
</html>
