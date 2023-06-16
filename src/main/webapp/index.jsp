<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <title>Irish Home Listings</title>
    <%@include file="includes/header.jsp"%>
</head>
<body class="container">
<br>
    <c:if test="${logged_in == \"user\"}">
        <h1>Welcome back ${user.getFirstName()}!</h1>
    </c:if>

    <c:if test="${logged_in == \"agent\"}">
        <h1>Welcome back ${agent.getFirstName()} !</h1>
    </c:if>

    <c:if test="${logged_in == null}">
        <h1 style="text-align: center"> Welcome to Irish Home Listings! </h1>
        <a href="ShowAllProperty"><p class="text-center">Show All Property!</p></a>
    </c:if>
</body>
<%@include file="includes/footer.jsp"%>

