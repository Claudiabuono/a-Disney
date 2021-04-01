/**
 * 
 */
$(document).ready(function() {
	
	$("#but1").click(function () {
		var nome = $("#nome").val();
		var prezzo = $("#prezzo").val();
		var quantita = $("#quantita").val();
		var prs = $("#prs").val();
		var url = $("#url").val();
		var iva = $("#iva").val();
		var sconto = $("#sconto").val();
		var tipo = $("#tipo").val();
		var categoria = $("#categoria").val();
		var des = $("#des").val();
		
		$.post("ProductAdminControl", {act: "insert", name : nome, price : prezzo, qty : quantita, personaggio: prs, url : url,iva : iva, sconto : sconto, tipo : tipo, categoria : categoria, description : des})
		.done(function(data){
			 $("#esito").append('<div id= "success5" class="alert success"><span class="closebtn">&times;</span><strong>Successo!</strong>Prodotto inserito correttamente</div>').hide().slideDown();
		})
		.fail(function() {
			 $("#esito").append('<div id= "alert5" class="alert"><span class="closebtn" onclick="this.parentElement.style.display="none";">&times;</span><strong>Errore!</strong> Errore durante l\'inserimento</div>').hide().slideDown();
		})
		.always(function () {
			$("#esito .closebtn").click(function () {
				$("#esito div").remove();
			})
		});
	});
});