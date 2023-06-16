<%--
  Created by IntelliJ IDEA.
  User: ivych
  Date: 21/11/2022
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Vendors</title>
    <%@include file="includes/header.jsp"%>
</head>
<body class="container">
    <table id="vendors" class="table-responsive">
        <thead>
        <tr>
            <th>Vendor Name: </th>
            <th>Vendor Phone Number: </th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${associatedVendors}" var="v">
            <tr>
                <td>${v.name}</td>
                <td>${v.phone}</td>
                <th><a href="EditVendor?id=${v.vendorId}">Edit Vendor</a></th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
<%@include file="includes/footer.jsp"%>