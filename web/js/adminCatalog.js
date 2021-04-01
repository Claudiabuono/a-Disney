$(document).ready(function(){
		var i = 0;
		
		function isEmpty(a) {
			return a == null || a==NaN || a=="";
		}
		
		$("#submit").click(function () {
			var pg = $(".pageof").val();
			$.get("admincat", {pg : pg})
				.done (function (json) {
					$("tbody tr").remove();
					
					$.each(json, function () {
						
						var row = '<tr id ="'+this.id+'" class = "text-center">'
						+ '<td class="product-remove"><button class="removeX" style="background-image: url(\'images/x.png\')"></button></td>'
						+ '<td class="product-name"> <h4>Prodotto: '+this.name+'<span></span></h4><button  class="button button2 submitter" type="submit">Modifica</button></td>'
						+ '<td><div id="img" style="background-image: url('+this.img+');"></div></td>'
						+ '<td role = "price">'+this.price+'&#8364;</td>'
						+ '<td role = "qty">'+this.qty+'</td>'
						+ '<td role = "discount">'+this.discount+'%</td>'
						+ '<td role = "iva">'+this.iva+'%</td>'
						+ '<td role = "character">'+this.character+'</td>'
						+ '<td role = "category">'+this.category+'</td>'
						+' </tr>';
						
						$("tbody").append(row);
					});
					
					$(".removeX").click(remove);
					$(".button").click(prova);
				})
				.fail (function () {
					alert("fail");
				});
		});
		
		//funzionante
		$(".removeX").click (remove);
		
		function remove () {
			var row = $(this).parents().filter("tr");
			var code = $(row).attr("id");
			
			$.post("ProductAdminControl", {act: "delete", id : code})
				.done(function () {
					$(row).remove();
				})
				.fail(function () {
					alert("Non e' stato possibile rimuovere il prodotto");
				})
		}
		
  		$(".button").click(prova);
  		
  		function prova (){
    		  i++;
    		  var up = "update"+ i;
    		  var row = $(this).parent().parent().addClass(up);
  		  var price = $("."+up+' [role="price"]').html();
  		  var qty = $("."+up+' [role="qty"]').html();
  		  var sconto = $("."+up+' [role="discount"]').html();
  		  var iva = $("."+up+' [role="iva"]').html();

  		  $("."+up+' [role="price"]').html('<input type = "number" size = "2">');
  		  $("."+up+' [role="qty"]').html('<input type = "number" size = "2">');
  		  $("."+up+' [role="discount"]').html('<input type = "number" size = "2">');
  		  $("."+up+' [role="iva"]').html('<input type = "number" size = "2">');
  		  var button = $("."+up+' .button');
  		  $(button).html('Update');
  		  $(button).unbind();
  		  $(button).click(function () {
  			  
  			  var code = $(this).parents().filter("tr").attr("id");
  			  var newPrice = $("."+up+' [role="price"] input').val();
  			  var newQty = $("."+up+' [role="qty"] input').val();
  			  var newSconto = $("."+up+' [role="discount"] input').val();
  			  var newIva = $("."+up+' [role="iva"] input').val();
  			  
  			  console.log(newPrice);
  			  
  			  if (isEmpty(newPrice) || isEmpty(newQty) || isEmpty(newSconto) || isEmpty(newIva)) {
  				    alert ("Uno dei campi di input non e' stato riempito");
  				  
  				    console.log(newPrice);
  				  	$("."+up+' [role="price"]').html(price);
  					$("."+up+' [role="qty"]').html(qty);
  					$("."+up+' [role="discount"]').html(sconto);
  					$("."+up+' [role="iva"]').html(iva);  
  					$(button).unbind();
  					$(button).html('Modifica');
  					$(button).click(prova);
  					
  					return;					
  			  }
  			  else {
  				  $(button).unbind();
  				  $.post ("ProductAdminControl", {act: "modify" ,code : code, iva: newIva, price: newPrice, qty: newQty, discount: newSconto})
  				  	.done(function (json) {
  				  		  $("."+up+' [role="price"]').html(json.newPrice);
  						  $("."+up+' [role="qty"]').html(json.newQty);
  						  $("."+up+' [role="discount"]').html(json.newSconto);
  						  $("."+up+' [role="iva"]').html(json.newIva);
  						 
  				  	})
  				  	.fail (function () {
  				  		alert("Modifica fallita!");
  				  		
  				  		$("."+up+' [role="price"]').html(price);
  						$("."+up+' [role="qty"]').html(qty);
  						$("."+up+' [role="discount"]').html(sconto);
  						$("."+up+' [role="iva"]').html(iva);
  				  	})
  				  	.always (function () {
  				  		  $(button).html('Modifica');
  						  $(button).click(prova);
  						  $('tbody '+"."+ up).removeClass(up);
  						  if($("input").length == 0)
  							  i = 0;
  				  	})
  				 
  			  }
  		  });
    		}
	});