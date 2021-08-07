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
				<td class="product-remove"><button class="removeX" ><span style="color: red;" class="glyphicon glyphicon-trash"></span></button></td>
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
		<p style = "text-align: center">pg <input class = "pageof" type = "number" value = "1" min="1" max="<%= request.getAttribute("maxPg") %>"> of <%= request.getAttribute("maxPg") %> <button id = "submit">Invia</button></p>
		</div>
	</div>
</div>
 </section>

<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header" style="padding:35px 50px;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4><span class="glyphicon glyphicon-lock"></span> Attenzione</h4>
			</div>
			<div class="modal-body" style="padding:40px 50px;">
					Sei sicuro di voler eliminare il prodotto?
					<button id="clacla" class="btn btn-success btn-block"> Si, elimina</button>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal"> No, torna indietro</button>
			</div>
		</div>

	</div>
</div>
