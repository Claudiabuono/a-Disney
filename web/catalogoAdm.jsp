<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% Boolean admin= (Boolean)session.getAttribute("isAdmin");
if(admin == null ? true : !admin.booleanValue()) {
	response.sendRedirect(response.encodeURL("Login.jsp"));
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="css/stile.css" type = "text/css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
 		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 		<title>Catalogo</title>
</head>

<body style="background-image: url('images/areg.gif');" >
<%@include file = "contentJSP/contentCatalogoAdmin.jsp"%>
<script type="text/javascript" src= "js/adminCatalog.js"></script>

</body>
</html>