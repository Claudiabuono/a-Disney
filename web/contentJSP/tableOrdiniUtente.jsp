<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="coreModels.beans.*, java.text.SimpleDateFormat"%>
<%
	List<FatturaBean> lista= (List<FatturaBean>) request.getAttribute("fatture"); 
	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
%>

<table class="table">
	     <thead class="thead-primary">
			<tr class="text-center">
				<th>&nbsp;</th>
				<th>Recenzione</th>
				<th>Prodotto</th>
				<th>nome Prodotto</th>
				<th>Prezzo</th>
				<th>Prezzo Iva</th>
				<th>Sconto</th>
				<th>Quantità</th>
			</tr>
			</thead>
		<tbody>
		
			<!--n ordini esistenti nel database-->
		 <%
		if (lista == null ? true : lista.size() == 0) {
			%>
			<tr>
				<td colspan = "8" style ="text-align: center">Nessun ordine risulta essere stato effettuato</td>
			</tr>
			<% 
		} else {
		 for (FatturaBean fatt : lista) {
			 int size = fatt.size();
			 java.util.List<Order> orders = fatt.getProdotti();
			 %> 
			<tr class="text-center">
				<td rowspan="<%=size%> " class="product-name">
				<p><%= format.format(fatt.getDate().getTime())%></p>
			<a href="FatturaPDF?id=<%=fatt.getCod()%>">	<button  class="button button2 submitter" type="submit">Fattura</button></a>
				</td>
				<%
				for(Order o : orders){
				 	ProductBean bean = o.getProduct();
				%>
				<td><a  class="button button2" href="ProductControl?id=<%=bean.getCode()%>&act=view">Aggiungi</a></td>
				<td><div id="img" style="background-image: url('<%=bean.getPhoto()%>');"></div></td>
				<td><%=bean.getName()%></td>
				<td><%=bean.getPriceSenzaIva()%></td>
				<td><%=bean.getPricewithIva()%></td>
				<td><%=bean.getDiscount()%></td>
				<td><%=o.getQty()%></td>
				</tr>
				<%} 
				}
			}%>
		</tbody>
			</table>
