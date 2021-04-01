

$(document).ready(function () {
	
	$(".productCard").hover(function(){
	      $(this).animate({width:"+=2%", margin:"-=1%"});
		}, function() {
	          $(this).animate({width:"-=2%", margin:"+=1%"},{
	        	    complete: function() { $(this).removeAttr('style') }
	        	});
	    })
});
