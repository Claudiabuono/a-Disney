<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%@ page import = "coreModels.beans.ProductBean" %>
<%@ page import = "java.util.List,java.util.ArrayList, coreModels.beans.RecensioneBean, java.util.Random"%>
    
<section class="card"  style="padding-bottom:90px;">
	<div style="float:left; padding:20px " >
     	<%@include file = "zoom.jsp" %>	
  	</div>
  	<div class="capo">
   		<h1>${product.getName()} <%@include file = "Voto.jsp" %></h1>
   		<br>
    	<h3 class="price"><span>${product.getPricewithIva()} &#8364</span></h3>
    	<p>La quantit&#224 disponibile in magazzino &#232 ${product.getQty()}</p>
    	<p>${product.getDescription()}</p>
    			
		<form method = "post" action="ProductControl?id=${product.code}&act=addC">
 		<%--@declare id="fname"--%><label  class="myLabel" for="fname">Quantit&#224 desiderata</label>
   		<input id="inputN" type="number" name="qty" value = "1" min="1" max="${product.getQty()}">
  			<br><button  type = "submit" class="button button2" >Acquista</button>
  			<a href="../../WebContent/Catalogo.jsp" class="button button2">Indietro</a>
  		</form>
		</div><br>
</section>
<section>
	<%@include file = "recensione.jsp" %>
</section>