<%--
  Created by IntelliJ IDEA.
  User: ivych
  Date: 21/11/2022
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Vendor</title>
    <%@include file="includes/header.jsp"%>
</head>
<body class="container">
    <form style="margin: auto;" method="post" action="UpdateVendor">
        <table style="margin: auto; ">
            <tr>
                <td>Vendor Name: </td>
                <td><input type="text" name="name" value="${vendorToEdit.name}"></td>
            </tr>
            <tr>
                <td>Vendor Phone Number: </td>
                <td><input type="text" name="phone" value="${vendorToEdit.phone}" maxlength="10"></td>
            </tr>
        </table>
        <p style="text-align: center;"><input type="submit" value="Confirm change"></p>
    </form>
</body>
</html>
