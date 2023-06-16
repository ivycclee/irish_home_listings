<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ivych
  Date: 29/10/2022
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0;">

    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
    <script>
        $(document).ready( function () {
            $('#properties').DataTable({ responsive: true });
            $('#agentProperties').DataTable( { responsive: true } );
            $('#archived').DataTable( { responsive: true } );
            $('#vendors').DataTable({ responsive: true });
            $('#favs').DataTable({ responsive: true });
        } );
    </script>
    <script src="${pageContext.request.contextPath}/includes/functions.js"> </script>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@300&display=swap" rel="stylesheet">

</head>
<body style="font-family: 'Ubuntu', sans-serif; color: #464655;">
<header>
    <nav class="navbar navbar-expand-md navbar-light" style="background-color: #D5CFE1; color: #464655; margin-bottom: 5%;">
        <a class="navbar-brand" href="index.jsp">Home</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="ShowAllProperty">View All Property</a>
                </li>

                <!-- want to only show login and register option if there is no user / agent logged in -->
                <c:if test="${logged_in == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="Login.jsp">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Register.jsp">Register</a>
                    </li>
                </c:if>

                <c:if test="${logged_in == \"user\"}" >
                <%-- options only available to a user --%>
                    <li class="nav-item">
                        <a class="nav-link" href="ViewFavourites">My Favourites</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Logout">Logout</a>
                    </li>
                </c:if>

                <c:if test="${logged_in == \"agent\"}" >
                    <%-- options only available to a user --%>
                    <li class="nav-item">
                        <a class="nav-link" href="ShowPropertyByAgent">Manage My Properties</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="ShowArchivedByAgent">My Archived Properties</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="ViewVendors">View Vendors</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Logout">Logout</a>
                    </li>
                </c:if>

            </ul>
        </div>
    </nav>
</header>