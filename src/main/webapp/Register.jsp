<%--
  Created by IntelliJ IDEA.
  User: ivych
  Date: 29/10/2022
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>

<head>
    <title>Register An Account!</title>
    <%@include file="includes/header.jsp"%>
</head>
<body class="container">
<div class="table-responsive" >
<form action="Register" method="post">
            <table class="text-center" style="width: 60%; margin: auto; margin-bottom: 2%;">
                <tr>
                    <td><label for="fname">First Name: </label></td>
                    <td><input type="text" id="fname" name="firstName" required></td>
                </tr>
                <tr>
                    <td><label for="lname">Last Name: </label></td>
                    <td><input type="text" id="lname" name="lastName" required></td>
                </tr>
                <tr>
                    <td><label for="email">Email: </label></td>
                    <td><input type="email" id="email" name="email" required></td>
                </tr>
                <tr>
                    <td><label for="password">Password: </label></td>
                    <td><input type="password" id="password" name="password" required></td>
                </tr>
                <tr>
                    <td><label for="confirmPassword">Confirm Password: </label></td>
                    <td><input type="password" id="confirmPassword" name="confirmPassword" required></td>
                </tr>
            </table>
        <p class="text-center"><input class="btn btn-outline-info" type="submit" value="Register"></p>
</form>
</div>
</body>
<%@include file="includes/footer.jsp"%>
