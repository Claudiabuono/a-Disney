<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<form class = "validation" id = "val" action = "Registration" method = "POST" name="modulo" onsubmit="return testpass(this)">
	<div class="field">
	<%
	Boolean error = (Boolean) request.getAttribute("error");
	if(error == null ? false : error.booleanValue()) {
		%>	
		<div class="error">
		<p>Warning<br>
			username gi? esistenti
		</p>
		</div>
	<%}%>
	<h1 class="h1disney"> Account Disney </h1>
	<h2 class="h2disney"> Non sei ancora iscritto? Registrati e potrai ricevere tutti gli aggiornamenti sui nuovi prodotti e offerte esclusive.</h2>
	
   <input type="text"  class="myInput1"  id = "name" size="20" name = "name" placeholder="Nome">
   <div id= "nameWarning" class="alert"><span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> <strong>Errore!</strong> Il nome deve essere una singola stringa di caratteri</div>

   <input type="text"  class="myInput1"  id = "surname" size="20" name = "surname" placeholder="Cognome">
   <div id= "surnameWarning" class="alert"><span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> <strong>Errore!</strong> Il cognome deve essere una singola stringa di caratteri</div>

   <input type="text"  class="myInput1"  id = "email" size="20" name = "email" placeholder="Email" autocomplete="off">
   <div id= "emailWarning" class="alert"><span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> <strong>Errore!</strong> email non valida</div>
   
   <input type="password"  class="myInput1"  id = "password" size="20" name = "password" placeholder="Password tra i 5 e i 10 caratteri" autocomplete="off">
   <div id= "passwordWarning" class="alert"><span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> <strong>Errore!</strong>la password non ? valida, deve essere tra 8 e 25 caratteri</div>

	<input type="password"  class="myInput1"  id = "password_2" size="20" name = "password_2" placeholder="Conferma password" autocomplete="off">

    <br> 
      <button class="button button2 submitter" type = "submit" style="width: 100%; text-decoration: none; border-radius:15px;">Crea Account</button>
       <a href= "Login.jsp" style="width: 100%; text-decoration: none; border-radius:15px;"> Sono registrato</a>
  </div>

	<script language="Javascript" type="text/javascript">
		function testpass(modulo){
			// Verifico che le due password siano uguali, in caso contrario avverto
			// dell'errore con un Alert
			if (modulo.password.value != modulo.password_2.value) {
				alert("La password inserita non coincide con la prima!")
				modulo.password.focus()
				modulo.password.select()
				return false
			}
			return true
		}
	</script>
 	
</form>