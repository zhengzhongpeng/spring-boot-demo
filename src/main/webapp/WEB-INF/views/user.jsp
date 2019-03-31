<%@page  language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <table align="center" border="1" cellspacing="0">
        <tr>
            <td>ID</td>
            <td>姓名</td>
            <td>别名</td>
            <td>电话</td>
            <td>邮箱</td>
        </tr>
        <c:forEach items="${userList}" var="user" >
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.alias}</td>
                <td>${user.phone}</td>
                <td>${user.email}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>