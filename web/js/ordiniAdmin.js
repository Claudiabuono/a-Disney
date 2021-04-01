$(document).ready(function () {
	$("#search").click(function () {
		var da = $('input[name = "da"]').val();
		var a = $('input[name = "a"]').val();
		var user = $('#mySearch').val();
		
		$.get("admin", {search : user, da : da, a : a})
			.done(function (data){
				$("table").remove();
				$($('.pageof').parent()).before(data);
			})
		
	});	
	
	$("#submit").click (function () {
		var da = $('input[name = "da"]').val();
		var a = $('input[name = "a"]').val();
		var user = $('#mySearch').val();
		var pg = $(".pageof").val();
		
		$.get("admin", {search : user, da : da, a : a, pg : pg})
			.done(function (data){
				$("table").remove();
				$($('.pageof').parent()).before(data);
			})
	})
	
	$("#mySearch").keydown(function myFunction() {
			$('#myDropdown p').remove();
			var src= $("#mySearch").val();
			$.getJSON("SuggesterUser", {srch : src})
				.done(function (json){
					$.each(json, function () {
						$("#myDropdown").append('<p role =' +this.login +'>'+this.login+'</p>');
						
						$('[role="'+this.login+'"]').click(function () {
							$("#mySearch").val($(this).html());
							document.getElementById("myDropdown").classList.toggle("show");
						});
					});
				});
			
		 	 document.getElementById("myDropdown").classList.toggle("show");
		 	 
		});
	

		// Close the dropdown if the user clicks outside of it
		window.onclick = function(event) {
		  if (!event.target.matches('.Mydropbtn')) {
		    var dropdowns = document.getElementsByClassName("Mydropdown-content");
		    var i;
		    for (i = 0; i < dropdowns.length; i++) {
		      var openDropdown = dropdowns[i];
		      if (openDropdown.classList.contains('show')) {
		        openDropdown.classList.remove('show');
		      }
		    }
		  }
		}
});