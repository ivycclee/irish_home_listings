<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ivych
  Date: 10/11/2022
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage My Properties</title>
    <%@include file="includes/header.jsp"%>
</head>
<body class="container justify-content-center">
<p style="text-align: center"><a href="InsertProperty"><button class="btn btn-outline-info">Add a property</button></a></p>
<br><br>
    <table id="agentProperties" class="table-responsive">
        <thead>
            <tr>
                <th>Listing No.</th>
                <th>Street</th>
                <th>City</th>
                <th>BER Rating</th>
                <th>Thumbnail</th>
                <th>Price</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${propertiesByAgentID}">
            <tr>
                <td>${p.listingNum}</td>
                <td>${p.street}</td>
                <td>${p.city}</td>

                <td><img src="assets/images/BER/${p.berRating == '0' or p.berRating == null or p.berRating == '' ? "EXEMPT" : p.berRating}.png"/></td>

                <td><img src="assets/images/properties/thumbs/${p.listingNum}.JPG" /></td>

                <td><fmt:formatNumber value="${p.price}" type="currency" currencySymbol="&euro;"/></td>
                <td><a href="EditProperty?id=${p.id}">Edit</a></td>
                <td><input type="button" class="btn btn-outline-info" onclick="ConfirmArchive('${p.id}')" value="Archive?"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
<%@include file="includes/footer.jsp"%>
