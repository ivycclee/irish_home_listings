<%--
  Created by IntelliJ IDEA.
  User: ivych
  Date: 11/11/2022
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Property</title>
    <%@include file="includes/header.jsp"%>
</head>
<body class="container">
<p style="text-align: center"><button class="btn btn-outline-info" onclick="history.back()">Go Back</button></p>

    <form action="UpdateProperty" method="post" enctype="multipart/form-data" style="margin: auto;">
        <table class="table-responsive" style="margin: auto; width: 60%;">
            <tr>
                <td class="col-2">Property ID</td>
                <td class="col-6"><input type="number" name="propertyID" value="${propertyToEdit.id}" readonly></td>
            </tr>
            <tr>
                <td class="col-2">Street</td>
                <td class="col-6"><input type="text" name="street" value="${propertyToEdit.street}"></td>
            </tr>
            <tr>
                <td class="col-2">City</td>
                <td class="col-6"><input type="text" name="city" value="${propertyToEdit.city}"></td>
            </tr>
            <tr>
                <td class="col-2">Listing No. </td>
                <td class="col-6"><input type="number" value="${propertyToEdit.listingNum}" readonly></td>
            </tr>
            <%-- Style ID needs dropdown --%>
            <tr>
                <td class="col-2">Style</td>
                <td class="col-6">
                    <select name="style">
                        <c:forEach var="s" items="${styles}">
                            <c:choose>
                                <c:when test="${s.getStyleId().equals(propertyToEdit.getStyleId())}">
                                    <option value="${s.getStyleId()}" selected>${s.getPStyle()}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${s.getStyleId()}">${s.getPStyle()}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <%-- Type ID needs dropdown --%>
            <td class="col-2">Property Type</td>
            <td class="col-6">
                <select name="type">
                    <c:forEach var="t" items="${propertyTypes}">
                        <c:choose>
                            <c:when test="${t.getTypeId().equals(propertyToEdit.getTypeId())}">
                                <option value="${t.getTypeId()}" selected>${t.getPType()}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${t.getTypeId()}">${t.getPType()}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
            <tr>
                <td class="col-2">Bedrooms</td>
                <td class="col-6"><input type="text" name="bedrooms" value="${propertyToEdit.bedrooms}"></td>
            </tr>
            <tr>
                <td class="col-2">Bathrooms</td>
                <td class="col-6"><input type="text" name="bathrooms" value="${propertyToEdit.bathrooms}"></td>
            </tr>
            <tr>
                <td class="col-2">Square Feet</td>
                <td class="col-6"><input type="text" name="squarefeet" value="${propertyToEdit.squarefeet}"></td>
            </tr>
            <%-- BER Rating needs dropdown --%>
            <tr>
                <td class="col-2">BER Rating</td>
                <td class="col-6">
                    <select name="berRating">
                        <c:forEach var="b" items="${ber}">
                            <c:choose>
                                <c:when test="${b.equals(propertyToEdit.getBerRating())}">
                                    <option value="${b}" selected>${b}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${b}">${b}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="col-2">Description</td>
                <td class="col-6"><textarea name="description" id="description">${propertyToEdit.description}</textarea></td>
            </tr>
            <tr>
                <td class="col-2">Lot Size</td>
                <td class="col-6"><input type="text" name="lotsize" value="${propertyToEdit.lotsize}"></td>
            </tr>
            <tr>
                <td class="col-2">Garage Size</td>
                <td class="col-6"><input type="text" name="garagesize" value="${propertyToEdit.garagesize}"></td>
            </tr>
            <%-- Garage ID needs dropdown --%>
            <tr>
                <td class="col-2">Garage Type</td>
                <td class="col-6">
                    <select name="garagetype">
                        <c:forEach var="g" items="${garageTypes}">
                            <c:choose>
                                <c:when test="${g.getGarageId().equals(propertyToEdit.getGarageId())}">
                                    <option value="${g.getGarageId()}" selected>${g.getGType()}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${g.getGarageId()}">${g.getGType()}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="col-2">Photo</td>
                <td class="col-6"><input type="file" name="files" multiple="true"></td>
            </tr>
            <tr>
                <td class="col-2">Price</td>
                <td class="col-6"><input type="number" name="price" value="${propertyToEdit.price}"></td>
            </tr>
        </table>

        <p style="text-align: center"><input type="submit" value="Update" class="btn btn-outline-info pt-2"></p>
    </form>
</body>
<%@include file="includes/footer.jsp"%>
