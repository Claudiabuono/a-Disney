<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<form class = "validation" id = "val" action = "Login" method = "POST">
	<div class="field">
		<%
			Boolean error = (Boolean) request.getAttribute("error");
			if(error == null ? false : error.booleanValue()) {
		%>

		<%}%>

		<h1 class="h1disney"> Account Disney </h1>
		<h1 class="h1disney"> Accedi </h1>
		<p></p><input  class="myInput1"  id = "username" type="email" size="30" name = "username" placeholder="username"></p>
		<p></p><input  class="myInput1"  id = "password" type="password" size="30" name = "password" placeholder="password"></p>
		<button class="button button2" type = "submit" style="border-radius:15px;">Accedi</button>
		<button class="button button2" type = "reset" style="border-radius:15px;">Reset</button><br><br>

		Non sei ancora registrato? &nbsp &nbsp<a href= "registration.jsp" style="width: 100%; text-decoration: none; border-radius:15px;"> Registrati ora</a>
	</div>

</form>

</form>