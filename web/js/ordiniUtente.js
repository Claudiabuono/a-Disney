/**
 * 
 */

$(document).ready(function () {
	$("#submit").click (function () {
		var pg = $(".pageof").val();
		var data = {op: "viewFatture", da: $('input[name = "da"]').val(), a : $('input[name = "a"]').val(), pg: pg};
		
		//avvia richiesta per reperire lista prodotti da visualizzare in pagina = pg
		$.get("UserManager", data)
			//se funge
			.done (function (data) {
				//rimuovi i prodotti dalla pagina
				$("table").remove();
				$("#title").after(data);
			})
			.fail(function ()  {
				$("tr").remove();
				$("tbody").append ('<tr><td colspan = "8" style = "text-align:center">la ricerca non ha prodotto alcun risultato</td></tr>');
			});
	});
	
	$("#selector").click(function() {
		var dattero = {op: "viewFatture", da: $('input[name = "da"]').val(), a : $('input[name = "a"]').val()} 
		
		$.get("UserManager", dattero)
			.done(function(data) {
				$("table").remove();
				$("#title").after(data);
				console.log(data);
						})
			.fail(function(){
				$("tr").remove();
				$("tbody").append ('<tr><td colspan = "8" style = "text-align:center">la ricerca non ha prodotto alcun risultato</td></tr>');
			})
			.always(function () {
				$(".pageof").val(1);
			});
	});
});