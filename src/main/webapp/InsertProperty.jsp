<%--
  Created by IntelliJ IDEA.
  User: ivych
  Date: 18/11/2022
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insert a property</title>
    <%@include file="includes/header.jsp"%>
</head>
<body class="container">
    <form action="AddPropertyToDB" method="post" enctype="multipart/form-data" style="margin: auto;">
        <table class="table-responsive" style="margin: auto;">
            <tr>
                <td>Street</td>
                <td><input type="text" name="street" required></td>
            </tr>
            <tr>
                <td>City</td>
                <td><input type="text" name="city" required></td>
            </tr>
            <tr>
                <td>Listing No. </td>
                <td><input type="number" name="listingNum" required></td>
            </tr>
            <%-- Style ID needs dropdown --%>
            <tr>
                <td>Style</td>
                <td>
                    <select name="style" required>
                        <c:forEach var="s" items="${styles}">
                            <option value="${s.getStyleId()}">${s.getPStyle()}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <%-- Type ID needs dropdown --%>
            <td>Property Type</td>
            <td>
                <select name="type" required>
                    <c:forEach var="t" items="${propertyTypes}">
                        <option value="${t.getTypeId()}">${t.getPType()}</option>
                    </c:forEach>
                </select>
            </td>
            <tr>
                <td>Bedrooms</td>
                <td><input type="number" name="bedrooms" value="0"></td>
            </tr>
            <tr>
                <td>Bathrooms</td>
                <td><input type="number" name="bathrooms" value="0"></td>
            </tr>
            <tr>
                <td>Square Feet</td>
                <td><input type="number" name="squarefeet" value="0"></td>
            </tr>
            <%-- BER Rating needs dropdown --%>
            <tr>
                <td>BER Rating</td>
                <td>
                    <select name="berRating" required>
                        <c:forEach var="b" items="${ber}">
                            <option value="${b}">${b}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Description</td>
                <td><textarea name="description" placeholder="Enter a description"></textarea></td>
            </tr>
            <tr>
                <td>Lot Size</td>
                <td><input type="text" name="lotsize"></td>
            </tr>
            <tr>
                <td>Garage Size</td>
                <td><input type="number" name="garagesize" value="0"></td>
            </tr>
            <%-- Garage ID needs dropdown --%>
            <tr>
                <td>Garage Type</td>
                <td>
                    <select name="garagetype" required>
                        <c:forEach var="g" items="${garageTypes}">
                            <option value="${g.getGarageId()}">${g.getGType()}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Photo</td>
                <td><input type="file" name="files" multiple="true"></td>
            </tr>
            <tr>
                <td>Price</td>
                <td><input type="number" name="price" step=".01" required></td>
            </tr>
            <%-- Vendor ID needs dropdown --%>
            <tr>
                <td>Vendor</td>
                <td>
                    <select name="vendor" required>
                        <c:forEach var="v" items="${vendors}">
                            <option value="${v.getVendorId()}">${v.getName()}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>

        <input type="submit" value="Add property">
    </form>
</body>
<%@include file="includes/footer.jsp"%>

