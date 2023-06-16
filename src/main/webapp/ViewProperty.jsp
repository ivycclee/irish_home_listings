<%@ page import="java.io.File" %>
<%@ page import="org.eclipse.persistence.internal.helper.type.IsBuildObjectCompleteOutcome" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ivych
  Date: 29/10/2022
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>

<head>
    <title>View Property</title>
    <%@include file="includes/header.jsp"%>
</head>
<body class="container table-responsive">

<p style="text-align: center"><button class="btn btn-outline-info" onclick="history.back()">Go Back</button></p>

<div id="imgCarousel" class="carousel slide" data-ride="carousal" style="width: 80%; margin: auto;">
    <div class="carousel-inner">
        <c:forEach var="pic" items="${photos}">
            <div class="carousel-item <c:if test="${pic == property.listingNum.toString().concat(\".JPG\") || pic == property.listingNum.toString().concat(\".jpg\")}" > active </c:if> ">
                <img src="assets/images/properties/large/${property.listingNum}/${pic}" class="border rounded img-fluid d-block w-100" style="height: 60%;">
            </div>
        </c:forEach>
    </div>

    <a class="carousel-control-prev" href="#imgCarousel" data-slide="prev">
        <span class="carousel-control-prev-icon"></span>
    </a>
        <a class="carousel-control-next" href="#imgCarousel" data-slide="next">
        <span class="carousel-control-next-icon"></span>
    </a>
</div>

    <table class="table-bordered" style="margin: auto; margin-top: 5%;">
        <tr>
            <td style="width: 35%">Listing No. </td>
            <td>${property.listingNum}</td>
        </tr>
        <tr>
            <td>Street </td>
            <td>${property.street}</td>
        </tr>
        <tr>
            <td>City </td>
            <td>${property.city}</td>
        </tr>
        <tr>
            <td>Bedrooms </td>
            <td>${property.bedrooms}</td>
        </tr>
        <tr>
            <td>Bathrooms </td>
            <td>${property.bathrooms}</td>
        </tr>
        <tr>
            <td>Square Feet </td>
            <td>${property.squarefeet}</td>
        </tr>
        <tr></tr>
        <tr>
            <td>Description </td>
            <td>${property.description}</td>
        </tr>
    </table>
        <%-- if a user is logged in, view notes   --%>
        <c:if test="${logged_in == \"user\"}">
            <p style="text-align: center;">Note:
            <c:forEach var="n" items="${allnotes}">
                <c:if test="${n.listingNum == property.listingNum && n.userId == user.userId}">
                    ${n.note}
                </c:if>
            </c:forEach>
                <p style="text-align: center;"><a href="FindNote?listingNum=${property.listingNum}&id=${property.id}">Edit Note</a></p>
        </c:if>

    <br><br>

    <p style="text-align: center"><button class="btn btn-outline-info"
    onclick="alert('${agentForThisProperty.firstName} ${agentForThisProperty.lastName}:  ${agentForThisProperty.phone}')">View Agent Contact Details</button></p>

    <br><br>
    <%-- google maps --%>
    <p style="text-align: center;"><iframe src="https://www.google.com/maps?q='${property.street},${property.city}'&output=embed" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe></p>
</body>
<%@include file="includes/footer.jsp"%>
