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
<link rel="stylesheet" href="css/stile.css" type = "text/css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bentornato</title>
</head>
<body  style="background-image: url('images/areg.gif');">

<form action="Aggiunta" method="post">
	<fieldset>
	<img  src="images/admin.jpg">
		<h1> Bentornato admin </h1>
	<h2>Cosa desideri effettuare?</h2> 
	<a href="admin">Visualizzare ordini dei clienti</a> <br>	
	<a href="admincat">Revisionare il catalogo</a> <br>	
	<a href="AddProdottiAdm.jsp">Aggiungi nuovi prodotti</a> <br>	
	<a href="Logout">Logout</a><br>	
	
	
	</fieldset>
	</form>

</body>
</html>