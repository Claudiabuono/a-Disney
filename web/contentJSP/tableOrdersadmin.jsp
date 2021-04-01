<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import = "coreModels.beans.*, java.util.Collection" %>

<table class="table" id="myMenu">
		 <thead class="thead-primary">
			<tr class="text-center">
				<th>&nbsp;</th>
				<th>Utente </th>
				<th>Data</th>
		     	<th>Prodotto</th>
				<th>nome Prodotto</th>
				<th>Prezzo</th>
				<th>Prezzo Iva</th>
				<th>Sconto</th>
				<th>Quantità</th>
			</tr>
			</thead>
		<tbody>
	 
		<%  
		java.util.List<FatturaBean> lista= (java.util.List<FatturaBean>) request.getAttribute("fatture");
	 	java.text.SimpleDateFormat format = new java.text.SimpleDateFormat ("yyyy-MM-dd");
	 	lista = lista == null ? new java.util.ArrayList<FatturaBean> () : lista;
		
		for (FatturaBean fatt : lista) {
			 int size = fatt.size();
			 java.util.List<Order> orders = fatt.getProdotti();
			 %> 
		
			 <tr class="text-center tr1">
				<td rowspan="<%=size%> " class="product-name">
				 <a href="FatturaPDF?id=<%=fatt.getCod()%>&search=<%=fatt.getUser().getLogin()%>"><button  class="button button2 submitter" type="submit">Fattura</button></a>
				</td>
				<td id="td1" rowspan="<%=size%> "><%=fatt.getUser().getLogin()%></td>
				<td rowspan="<%=size%> "><%=format.format(fatt.getDate().getTime()) %></td>
		     	
			<%
		
				for(Order o : orders){
				 	ProductBean bean = o.getProduct();
			%>
				<td><div id="img" style="background-image: url('<%=bean.getPhoto()%>');"></div></td>
				<td><%=bean.getName()%></td>
				<td><%=bean.getPriceSenzaIva()%></td>
				<td><%=bean.getPricewithIva()%></td>
				<td><%=bean.getDiscount()%></td>
				<td><%=o.getQty()%></td>
			
			</tr>
		<%}} %>
			
		</tbody>
			</table>