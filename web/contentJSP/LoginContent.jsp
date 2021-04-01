<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<form id = "val" action="Login" method="post">
	<fieldset>
	<h1> Account Disney </h1>
	<% 
	Boolean warning = (Boolean) request.getAttribute("warning");
	if (warning == null ? false : warning) {
	%>
		<div id="alert3" class="alert" style=""><span class="closebtn" onclick="this.parentElement.style.display='none';">×</span> <strong>Errore!</strong> Nome utente o password non corretti</div>
	<%}
	%>
	<h2>Accedi</h2>
	<p><input class="myInput1" id="username" type="email" size="30" name = "username" placeholder="Email" autocomplete="off"><br></p>
    <p><input class="myInput1" id="password" type="password" size="30" name = "password" placeholder="Password tra i 5 e i 10 caratteri" autocomplete="off"><br></p>
  		<button class="button button2" type = "submit" >Accedi</button>
  		<button class="button button2" type = "reset">Reset</button><br><br>
	Non sei ancora registrato? &nbsp &nbsp<a href= "../../WebContent/registration.jsp"> Registrati ora</a>
	</fieldset>
	</form>
	