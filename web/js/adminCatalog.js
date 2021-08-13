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
						+ '<td class="product-remove"><button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal" data-product="<%=bean.getCode()%>"><span style="color: red;" class="glyphicon glyphicon-trash"></span></button></td>'
						+ '<td class="product-name"> <h4>Prodotto: '+this.name+'<span></span></h4><button  class="button button2 submitter" type="submit" style="border-radius:15px;">Modifica</button></td>'
						+ '<td><div id="img" style="background-image: url('+this.img+');"></div></td>'
						+ '<td role = "price">'+this.price+'</td>'
						+ '<td role = "qty">'+this.qty+'</td>'
						+ '<td role = "discount">'+this.discount+'</td>'
						+ '<td role = "iva">'+this.iva+'</td>'
						+ '<td role = "character">'+this.character+'</td>'
						+ '<td role = "category">'+this.category+'</td>'
						+' </tr>';
						
						$("tbody").append(row);
					});

					$(".button").click(prova);
				})
				.fail (function () {
					alert("fail");
				});
		});

		
  		$(".button").click(prova);


  		
  		function prova (){
    		  i++;
    		  var up = "update"+ i;
    		  var row = $(this).parent().parent().addClass(up);
  		  var price = $("."+up+' [role="price"]').html();
  		  var qty = $("."+up+' [role="qty"]').html();
  		  var sconto = $("."+up+' [role="discount"]').html();
  		  var iva = $("."+up+' [role="iva"]').html();

		  $("."+up+' [role="price"]').html('<input type="number"  min="0" step=".01" size = "2" value='+price+'>');
  		  $("."+up+' [role="qty"]').html('<input type = "number" min="0" size = "2" value='+qty+'>');
  		  $("."+up+' [role="discount"]').html('<input type="number"  min="0" step=".01" size = "2" value='+sconto+'>');
  		  $("."+up+' [role="iva"]').html('<input type="number"  min="0" step=".01" size = "2" value='+iva+'>');
  		  var button = $("."+up+' .button');
  		  $(button).html('Conferma Modifiche');
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
  			  }else {
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