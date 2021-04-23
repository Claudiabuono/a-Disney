<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div class="field2">
	
		<%--@declare id="fname"--%><h3>Dati Personali </h3>
 		<label  class="myLabel" for="fname"><i class="fa fa-user"></i> Nome</label>
    	<input class="myInput1" type="text" id="name" name="firstname" value="${user.name}" placeholder="${user.name}" >
    	<div id= "nameWarning" class="alert"><span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span><strong>Errore!</strong> Nome non valido, deve essere costituito da soli caratteri e senza spazi bianchi</div>
  
    	<label  class="myLabel" for="fname"><i class="fa fa-user"></i> Cognome</label>
    	<input class="myInput1" type="text" id="surname" name="firstname" value="${user.cognome}" placeholder="${user.cognome}" >
    	<div id= "surnameWarning" class="alert"><span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span><strong>Errore!</strong> Cognome non valido, deve essere costituito da soli caratteri e senza spazi bianchi</div>
  
    	<button id="but1" class=" button button2">Modifica</button>
    	
    	<div id= "success1" class="alert success" ><span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span><strong>Successo!</strong> Nome e/o Cognome aggiornati correttamente</div>
		<div id= "alert1" class="alert"><span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span><strong>Errore!</strong> Errore durante l aggiornamento</div>
     <hr>
     
    	<h3>Credenziali</h3>
    	<i class="fa fa-envelope"></i> Email: ${user.login}<br><br>
		<label  class="myLabel" for="fname"><i class="fa fa-lock"></i>Password</label>
    	<input class="myInput1" type="text" id="vpass" name="firstname">
    	<label  class="myLabel" for="fname"><i class="fa fa-lock"></i>Ripeti Password</label>
    	<input class="myInput1" type="text" id="password" name="firstname">
    	<div id= "passwordWarning" class="alert"><span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span><strong>Errore!</strong> Password non valida, deve essere tra 8 e 25 caratteri</div>
  
    	<button id="but2" class="but2 button button2">Modifica Password</button>
    	<div id= "success2" class="alert success"><span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span><strong>Successo!</strong> Password aggiornata correttamente</div>
		<div id= "alert2" class="alert"><span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> <strong>Errore!</strong> Errore durante l aggiornamento</div>
		<div id= "alert3" class="alert"><span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> <strong>Errore!</strong> La password non corrisponde a quella digitata</div>
	<hr>
		<h3>Aggiungi nuovo indirizzo</h3>
		 <%@ include file = "newAddress.jsp" %> 
</div>