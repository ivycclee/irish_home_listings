<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ivych
  Date: 22/11/2022
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Favourites</title>
    <%@include file="includes/header.jsp"%>
</head>
<body class="container">
    <c:if test="${fn:length(favourites) > 0}">
    <table id="favs" class="table-responsive">
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
        <c:forEach var="f" items="${favourites}">
            <tr>
                <td>${f.listingNum}</td>
                <td>${f.street}</td>
                <td>${f.city}</td>

                <td><img src="assets/images/BER/${f.berRating == '0' or f.berRating == null or f.berRating == '' ? "EXEMPT" : f.berRating}.png"/></td>

                <td><img src="assets/images/properties/thumbs/${f.listingNum}.JPG" /></td>

                <td><fmt:formatNumber value="${f.price}" type="currency" currencySymbol="&euro;"/></td>
                <td><a href="PropertyDrilldown?id=${f.id}">View More</a></td>

                <td><a href="RemoveFromFavourites?id=${f.id}">Remove from favourites</a></td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
    </c:if>

    <c:if test="${fn:length(favourites) == 0}">
        <h2>You have no favourites</h2>
    </c:if>
</body>
<%@include file="includes/footer.jsp"%>

