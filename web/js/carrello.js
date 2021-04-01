/**
 * 
 */

$(document).ready(function () {
		//avvia richiesta per reperire lista prodotti 
		$.get("myCart")
			//se funge
			.done (function (data) {
				$(".leftcolumn").append(data);
				
				$('.quantity input[type = "number"]').focus (function focuser () {
					var oldQty = this.value;
					$(this).unbind();
					$(this).blur(function () {
						var row = $(this).parents().filter("tr");
						var qty = this.value;
						var id = $(row).attr("id");
						
						$.get("ProductControl", {id : id, act : "addC", qty : qty})
							.done (function (json) {
								console.log(json);
								var t= 0;
								var flag = false;
								for(i=0;i<json.size && !flag;i++){
									if(json.list[i].order.id==id){
										t = json.list[i].totOrdine;
										flag = true;
									}
								}
								$("#"+id +' .total').html(t);
								ordini(json);
							})
							.fail (function () {
								//ancora in cantiere
								var o = $("#"+$(row).attr("id")+' .quantity input[type = "number"]');
								$(o).val(oldQty);
							})
							.always(function () {
								var o = $("#"+$(row).attr("id")+' .quantity input[type = "number"]');
								$(o).unbind();
								$(o).focus(focuser);
							});
					});
				});

				
				$(".removeX").click(function () {
					
					var row = $(this).parents().filter("tr");
					$.get("ProductControl", {id : $(row).attr("id"), act : "delete" }) 
						.done(function(json){
							$(row).remove();
							ordini(json);
						});
				});
				
				function ordini(json)
				{ 
					$("#Iva span").html(json.totIva +"&#8364");
					$("#noIva span").html(json.noIva +"&#8364");
					$("#tot span").html(json.tot+"&#8364");
					$("#size b").html(json.size+"&#8364");
				}
			});
		
		
		
});