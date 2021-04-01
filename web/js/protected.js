/**
 * 
 */
$(document).ready(function() {
	$("#success1").hide();
	$("#alert1").hide();
	$("#success2").hide();
	$("#alert2").hide();
	$("#alert3").hide();
	
	
	$("#but1").click(function () {

		var nome = $("#name").val();
		var cognome = $("#surname").val();
		
		if (nameVerifier ()) {
		
		$.post("UserManager", {op: "modCred", nome: nome, cognome: cognome})
		.done(function(data){
			 $("#success1").slideDown();
		})
		.fail(function() {
			 $("#alert1").slideDown();
		});
		}
	});
	
	$("#but2").click(function () {
		var pw = $("#vpass").val();
		var oldpw= $("#password").val();
		if(oldpw==pw && validatePassword ()){
			$.post("UserManager", {op: "modCred", pass: pw}, "html")
			.done(function(data){
				 $("#success2").slideDown();
			})
			.fail(function() {
				 $("#alert2").slideDown();
			});
		}else{
			 $("#alert3").slideDown();
		}
		
		
	});
	
	
});