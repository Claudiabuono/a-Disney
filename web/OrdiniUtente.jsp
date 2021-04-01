<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <% Boolean user= (Boolean)session.getAttribute("isUser");
if(user == null ? true : !user.booleanValue()) {
	response.sendRedirect(response.encodeURL("Login.jsp"));
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
	<title>Ordini effettuati</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta charset="utf-8">
 	<meta name="viewport" content="width=device-width, initial-scale=1">
 	<link rel="stylesheet" href="css/stile.css" type = "text/css">
  	<link rel="stylesheet" href="css/bootstrap.css" type = "text/css">
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
 </head>
<body style="background-image: url('images/areg.gif');" data-spy="scroll" data-target=".navbar" data-offset="50">
<%@include file = "header2.jsp" %>
<%@include file = "contentJSP/contentOrdiniUtente.jsp"%>
<%@include file = "footer.jsp" %>
<script type="text/javascript" src= "js/ordiniUtente.js"></script>

</body>
</html>