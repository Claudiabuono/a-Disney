/**
 * 
 */

$(document).ready(function() {
	
$(".alert").hide();
    
    
function verifier (element, formatter, warning) {
		if(element.value.match(formatter))
			return true;
		else {
		
		$(warning).slideDown();
		return false;
	}

}

function validation () {
	var address = document.getElementById("adr");
	var city = document.getElementById("city"); 
	var provincia = document.getElementById("provincia");
	var state = document.getElementById("state");
	var caratteri = /^[A-Za-z\s]+$/;
	
	var cap = document.getElementById("CAP"); /*cap.lenght==5*/
	var max = /^\d{5}$/;
	
	var flag = true;

			/*pss no valida;*/

	if (address != null) {
		if(!verifier(address, caratteri , document.getElementById("AddressWarning")))
			flag = false;
	}
		
	if(city != null){
		if(!verifier(city,caratteri, document.getElementById("cityWarning")))
			flag = false;
	}
		
	if (provincia != null){	
		if(!verifier(provincia, caratteri, document.getElementById("provinciaWarning")))
			flag = false;
	}
	
	if (state != null){	
		if(!verifier(state, caratteri, document.getElementById("statoWarning")))
			flag = false;
	}
	if (cap != null){
		if(!verifier(cap, max, document.getElementById("capWarning")))
			flag = false;
	}
	
	return flag;
}




$("input").focus (function focuser () {
this.style.border = "2px solid #a3d1ff";
this.style.backgroundColor = "#a3d1ff";
$('#'+$(this).attr("id")+'Warner').slideUp();
});

$("input").blur (function blurer ()
{
this.style.border = "";
this.style.backgroundColor = "";
});
	
	$("#AddIndirizzo").hide();
	$("#toggle").click(function(){
		  $("#AddIndirizzo").slideToggle();
		});
	$("#butt").click(function () {
		var via = $("#adr").val();
		var nC = $("#nc").val();
		var ct= $("#city").val();
		var cap= $("#CAP").val();
		var st= $("#state").val();
		var provincia= $("#provincia").val();
		
		if (validation ()) {
		$(".alert").slideUp();
		$.post("AddressOperations", {operation: "1", via: via, ncv: nC, citta: ct, provincia: provincia , cap: cap, stato: st}, "html")
		.done(function(data){
			var string = '<option value="'+data.codice+'">Indirizzo:'+data.street+', ' +data.nCv+", CAP:"+data.CAP+"; citta\'" +data.citta+'</option>';
			$("#address").append(string);
			$("#successAddr").slideDown();
		})
		.fail(function() {
			
			$("#alertAddr").slideDown();
		});
		}
	});


	
});