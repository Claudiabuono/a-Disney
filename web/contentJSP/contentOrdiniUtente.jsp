<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div class="field2">
	<div class="row ">
    	<div class="card x">
    	<div>
    			<input type="date" class="ricerca"  name="da" placeholder="da data..." style="background-image: url('/images/dateFrom.png')">
 	    		<input type="date" class="ricerca" name="a" placeholder="a data..." style="background-image: url('/images/dateTo.png')">
    			<button id = "selector" class="button button2" style="border-radius:15px;">Cerca</button>
    		
    	</div>
    	<div class="cart-list">
    	<h1 id = "title">Ordini effettuati</h1>
      	<%@include file = "tableOrdiniUtente.jsp" %>
      	<p style = "text-align: center">pagina <input class = "pageof" type = "number" value = "1" min="1" max="<%= request.getAttribute("maxPg") %>"> di <%= request.getAttribute("maxPg") %> <button id = "submit" style="background-color: #1E90FF;border: none;color: white;border-radius:15px; width: 80px;height: 30px;">Invia</button></p>
		</div>
 	</div>
 	</div>
</div>
