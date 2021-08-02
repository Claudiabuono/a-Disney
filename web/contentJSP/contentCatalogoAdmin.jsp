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
			<a href="Logout">Logout</a>
    	<h1>Catalogo</h1>
	    	<table class="table">
	     <thead class="thead-primary">
			<tr class="text-center">
				<th>&nbsp;</th>
				<th>&nbsp;</th>
				<th>Foto</th>
		     	<th>Prezzo</th>
				<th>Quantit&#224;</th>
				<th>Sconto</th>
				<th>Iva</th>
				<th>Personaggio</th>
				<th>Categoria</th>
				
			</tr>
			</thead>
		<tbody>
	<%	if (list == null) {
		%>
		<tr>
		<td colspan = "8" style = "text-align: center">no products selected</td>
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
				<td class="product-remove"><button class="removeX" style="background-image: url('/images/x.png')"></button></td>
				<td class="product-name">
					<h4>Prodotto: <%=bean.getName()%><span></span></h4>
					<button  class="button button2 submitter" type="submit">Modifica</button>
				</td>
				<td><div id="img" style="background-image: url('<%=bean.getPhoto()%>');"></div></td>
				<td role = "price"><%=bean.getPrice()%>&#8364;</td>
				<td role = "qty"><%=bean.getQty()%></td>
				<td role = "discount"><%=bean.getDiscount()%>%</td>
				<td role = "iva"><%=bean.getIva()%>%</td>
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