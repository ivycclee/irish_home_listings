<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ivych
  Date: 16/11/2022
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Note</title>
    <%@include file="includes/header.jsp"%>
</head>
<body class="container" style="text-align: center">

<p style="text-align: center"><button class="btn btn-outline-info" onclick="history.back()">Go Back</button></p>

<c:choose>
    <c:when test="${noteToEdit != null}">
        <h2 style="text-align: center;">Edit the note</h2>
        <form style="width: 60%; margin: auto;" action="ChangeNote" method="post">
            <p style="text-align: center;"><textarea name="edit">${noteToEdit.note}</textarea></p>
            <br>
            <p style="text-align: center;"><input type="submit" class="btn btn-outline-info" name="submission" value="Edit"></p>
        </form>
    </c:when>
    <c:otherwise>
        <h2 style="text-align: center;">Add a new note</h2>
        <form style="width: 60%; margin: auto;" action="AddNote" method="post">
            <p style="text-align: center;"><textarea name="add" placeholder="Add a new note"></textarea></p>
            <br>
            <p style="text-align: center;"><input type="submit" name="submission" class="btn btn-outline-info" value="Add"></p>
        </form>
    </c:otherwise>
</c:choose>
</body>
</html>
