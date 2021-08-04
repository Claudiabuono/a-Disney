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
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link rel="stylesheet" href="css/bootstrap.css" type = "text/css">
	<link rel="stylesheet" href="css/stile.css" type = "text/css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	<title>Bentornato</title>
</head>
<body  style="background-image: url('images/areg.gif');">
<%@include file = "header2.jsp" %>
<section class="row" style="padding-top: 30px;">
	<section class="leftcolumn">
		<div class="card">
			<img src="images/admin.jpg">
		</div>
	</section>
	<section class="rightcolumn" style="background-image: url('/images/areg.gif');">

		<div class="card">
			<h1> Bentornato admin </h1>
			<a class= "button button2" style="width: 100%; text-decoration: none" href="admin">Visualizzare ordini dei clienti</a> <br>
			<a class= "button button2" style="width: 100%; text-decoration: none" href="admincat">Revisionare il catalogo</a> <br>
			<a class= "button button2" style="width: 100%; text-decoration: none" href="AddProdottiAdm.jsp">Aggiungi nuovi prodotti</a> <br>
            <a class= "button button2" style="width: 100%; text-decoration: none" href="Logout">Logout</a><br>
     </div>
	</section>
</section>

<%@include file = "footer.jsp" %>
</body>
</html>