<%@ page import="shestakov.models.User" %>
<%@ page import="shestakov.postgresql.DBUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>

<form action="${pageContext.servletContext.contextPath}/" method="POST" >
    <c:set var="inputDisplay" value="${role.name}" />
    <c:choose>
        <c:when test="${inputDisplay == 'root'}">
            <c:forEach items="${users}" var="user">
                <input type="radio" name="user" value="${user.login}" > "${user}"
                <br/>
            </c:forEach>
            <br/>
            <input type='submit' value='edit' name='edit' />
            <input type='submit' value='delete' name='delete' />
            <input type='submit' value='edit role' name='editRole' />
            <td><p><a href="${pageContext.servletContext.contextPath}/create" > Add new user</a></p></td>
        </c:when>
        <c:otherwise>
            <c:forEach items="${users}" var="user">
                <c:if test="${user.login == login}">
                    <input type="radio" name="user" value="${user.login}" > "${user}"
                    <br/>
                </c:if>
            </c:forEach>
            <br/>
            <input type='submit' value='edit' name='edit' />
            <input type='submit' value='delete' name='delete' style="display:none;" />
            <input type='submit' value='edit role' name='editRole' style="display:none;" />
        </c:otherwise>
    </c:choose>
</body>
</html>
