<%--
  Created by IntelliJ IDEA.
  User: ivych
  Date: 29/10/2022
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>

<head>
    <title>Please Log In</title>
    <%@include file="includes/header.jsp"%>
</head>
<body class="container">
    <form action="Login" method="POST">
        <table class="text-center" style="width: 60%; margin: auto; margin-bottom: 2%;">
            <tr>
                <td><label for="email">Email:</label></td>
                <td><input type="text" id="email" name="email" required/></td>
            </tr>
            <tr>
                <td><label for="pass">Password:</label></td>
                <td><input type="password" id="pass" name="password" required/></td>
            </tr>
        </table>
        <p class="text-center"><input class="btn btn-outline-info" type="submit" value="Login" /></p>
    </form>
</body>
<%@include file="includes/footer.jsp"%>


