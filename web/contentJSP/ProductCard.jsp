<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.List, coreModels.beans.ProductBean"%>

<%
	List<ProductBean> list =(List<ProductBean>)  request.getAttribute("list");
%> 
<div class = "container flex-container">
<%
if (list == null ? true : list.size() == 0) {
	%>
	<p style = "text-align: center">la ricerca non ha prodotto risultati</p>
	<% 
} else {
	for (ProductBean e : list) { %>
	
 	<div class="productCard" >
		<img  class="imgSconto2" src="<%=e.getPhoto()%>" >
		<div class ="nomeProdSconto"><h3 style = "font-size: 120%"><a href = "ProductControl?id=<%=e.getCode()%>&act=view"><%=e.getName() %></a></h3></div>
 		<%if(e.isinDiscount()) {
 			%><p class="prezzo"><%=e.getPriceSenzaSconto()%>&#8364;</p>
 		<% }else {%>
 			<p class="prezzo"></p>
 		<%}%>
  		<p class="sconto"><%=e.getPricewithIva()%>&#8364;</p>
	</div>
<%}
}%>
</div>
