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
  
    	<button id="but1" class=" button button2" style="width: 100%;border-radius:15px;">Modifica</button>
    	
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
  
    	<button id="but2" class="but2 button button2" style="width: 100%;border-radius:15px;">Modifica Password</button>
    	<div id= "success2" class="alert success"><span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span><strong>Successo!</strong> Password aggiornata correttamente</div>
		<div id= "alert2" class="alert"><span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> <strong>Errore!</strong> Errore durante l aggiornamento</div>
		<div id= "alert3" class="alert"><span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> <strong>Errore!</strong> La password non corrisponde a quella digitata</div>
	<hr>

	<h3>Indirizzi</h3>
	<%@ include file = "newAddress.jsp" %>
	<%@ include file = "visualizzaIndirizzi.jsp" %>

</div>
<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Attenzione!</h4>
			</div>
			<div class="modal-body">
				<p>Sei sicuro di voler eliminare l'indirizzo?</p>
			</div>
			<div class="modal-footer">
				<form id="ciao">
					<input id="id" type="hidden"/>
					<button id = "save"  form="ciao" type="submit" class="btn btn-primary">Si, elimina indirizzo</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Chiudi</button>
				</form>
			</div>
		</div>

	</div>
</div>
<script>
	$(document).ready(() => {
		$('#myModal').on('show.bs.modal', function (event) {
			let button = $(event.relatedTarget) // Bottone che triggera la modal
			let prod = button.data('product') // Estrai info dal data-product
			let modal = $(this)

			modal.find(".modal-footer form #id").val(prod)
		})

		$('#myModal .modal-footer form').submit((e) => {
			e.preventDefault()
			let id= $('#id').val()
			let row= $('tbody #' +id)
			$.post("AddressOperations", {operation: "3", code : id})
					.done(function () {
						row.remove();
					})
					.fail(function () {
						alert("Non e' stato possibile rimuovere l'indirizzo");
					})
			$('#myModal').modal('hide')
		})

		//scratch this shit let's go with form and use input type hidden solution
		$("#myModal").on("hidden.bs.modal", () => {
			let modal = $(this)
			let btn = modal.find(".modal-footer .btn-primary")
			modal.find("#myModal #id").val('')
		})
	})
</script>