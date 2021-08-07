<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/stile.css" type = "text/css">
	<link rel="stylesheet" href="css/bootstrap.css" type = "text/css">
	<script type = "text/javascript" src = "js/jquery.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>

<body style="background-image: url('images/areg.gif');" data-spy="scroll" data-target=".navbar" data-offset="50">
	<%@include file = "header2.jsp" %><br><br>
	<div id="errore" class="field2">
		<h1> Errore </h1>

	<h2>Ci dispiace. Si è verificato un errore, la pagina a cui stai tentando di accedere è privata o inesistente</h2>
	<a class="button button2" href= "index.jsp" style="width: 100%; text-decoration: none; border-radius:15px;"> Home</a>
	</div>
	<%@include file = "footer2.jsp" %>
	
</body>
</html>