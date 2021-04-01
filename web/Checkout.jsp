<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<%
Boolean User = (Boolean) session.getAttribute("isUser");
if ((User == null ? true : !User)) 
	response.sendRedirect (response.encodeURL ("error.jsp"));
%>
<!DOCTYPE>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta charset="utf-8">
 	<meta name="viewport" content="width=device-width, initial-scale=1">
 	<link rel="stylesheet" href="css/stile.css" type = "text/css">
  	<link rel="stylesheet" href="css/bootstrap.css" type = "text/css">
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<title>Checkout</title>
</head>
<body style="background-image: url('images/areg.gif');" data-spy="scroll" data-target=".navbar" data-offset="50">
		<%@include file = "header2.jsp" %><br>
		<%@include file = "contentJSP/checkoutContent.jsp" %>
		<%@include file = "footer.jsp" %>
<script type="text/javascript" src= "js/credenziali.js"></script>

</body>
</html>
