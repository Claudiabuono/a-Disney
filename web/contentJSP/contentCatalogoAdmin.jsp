<%@page import="coreModels.beans.ProductBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
	java.util.List<ProductBean> list = (java.util.List<ProductBean>) request.getAttribute("list");
	
%>

<!--Tutti i prodotti nel catalogo-->
<section class="ftco-section ftco-cart">
	<div class="row ">
    	<div class="card x">
    	<div class="cart-list">
    	<h1>Catalogo</h1>
	    	<table class="table">
	     <thead class="thead-primary">
			<tr class="text-center">
				<th>&nbsp;</th>
				<th>&nbsp;</th>
				<th>Foto</th>
		     	<th>Prezzo &#8364;</th>
				<th>Quantit&#224;</th>
				<th>Sconto %</th>
				<th>Iva %</th>
				<th>Personaggio</th>
				<th>Categoria</th>
				
			</tr>
			</thead>
		<tbody>
	<%	if (list == null) {
		%>
		<tr>
		<td colspan = "8" style = "text-align: center">non ci sono prodotti selezionati</td>
		</tr>
		<% 
	} else {
	
		for (coreModels.beans.ProductBean bean : list) { 
			int ctgy= bean.getCategory();
			String c="";
  			if("0".equals(ctgy+c)){ c="Articoli per la casa";}
  			if("1".equals(ctgy+c)){ c="Abbigliamento e Accessori";}
  			if("2".equals(ctgy+c)){ c="Articoli per le feste";}
  			if("3".equals(ctgy+c)){ c="Giochi";}
	 %> 
		<tr id = "<%=bean.getCode()%>" class="text-center">
				<td class="product-remove">
					<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal" data-product="<%=bean.getCode()%>"><span style="color: red;" class="glyphicon glyphicon-trash"></span></button>
				</td>
				<td class="product-name">
					<h4>Prodotto: <%=bean.getName()%><span></span></h4>
					<button  class="button button2 submitter" type="submit" style="border-radius:15px;">Modifica</button>
				</td>
				<td><div id="img" style="background-image: url('<%=bean.getPhoto()%>');"></div></td>
				<td role = "price"><%=bean.getPrice()%></td>
				<td role = "qty"><%=bean.getQty()%></td>
				<td role = "discount"><%=bean.getDiscount()%></td>
				<td role = "iva"><%=bean.getIva()%></td>
				<td role = "character"><%=bean.getCharacter()%></td>
				<td role = "category"><%=c %></td>

			</tr><%}

		}%>
		</tbody>
		</table>
	  		<p style = "text-align: center">pagina <input class = "pageof" type = "number" value = "1" min="1" max="<%= request.getAttribute("maxPg") %>"> di <%= request.getAttribute("maxPg") %> <button id = "submit" style="background-color: #1E90FF;border: none;color: white;border-radius:15px; width: 80px;height: 30px;">Invia</button></p>
		</div>
	</div>
</div>
 </section>

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
				<p>Sei sicuro di voler eliminare il prodotto?</p>
			</div>
			<div class="modal-footer">
				<form id="ciao">
					<input id="id" type="hidden"/>
					<button id = "save"  form="ciao" type="submit" class="btn btn-primary">Si, elimina prodotto</button>
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
			$.post("ProductAdminControl", {act: "delete", id : id})
				.done(function () {
					row.remove();
				})
				.fail(function () {
					alert("Non e' stato possibile rimuovere il prodotto");
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
