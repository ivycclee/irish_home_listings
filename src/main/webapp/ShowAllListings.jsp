<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ivych
  Date: 29/10/2022
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>

<head>
    <title>All Listings</title>
    <%@include file="includes/header.jsp"%>
</head>
<body class="container justify-content-center table-responsive">
    <table id="properties" class="">
        <thead>
            <tr>
                <th>Listing No.</th>
                <th>Street</th>
                <th>City</th>
                <th>BER Rating</th>
                <th>Thumbnail</th>
                <th>Price</th>
                <th></th>

                <%-- if user logged in, give option to add to fav --%>
                <c:if test="${logged_in == \"user\"}">
                    <th></th>
                </c:if>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${allProperties}">
            <tr>
                <td>${p.listingNum}</td>
                <td>${p.street}</td>
                <td>${p.city}</td>

                <td><img src="assets/images/BER/${p.berRating == '0' or p.berRating == null or p.berRating == '' ? "EXEMPT" : p.berRating}.png"/></td>

                <td><img src="assets/images/properties/thumbs/${p.listingNum}.JPG" /></td>

                <td><fmt:formatNumber value="${p.price}" type="currency" currencySymbol="&euro;"/></td>
                <td><a href="PropertyDrilldown?id=${p.id}">View More</a></td>
                
                <%-- if user logged in, give option to add to fav --%>
                <c:if test="${logged_in == \"user\"}">
                    <td><span onclick="AddToFav(${user.userId}, ${p.id})"><i id="fav" class="fa fa-heart-o" aria-hidden="true"></i></span></td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
<%@include file="includes/footer.jsp"%>



