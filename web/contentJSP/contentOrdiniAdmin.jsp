<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!--Attenzione chiedere se bisogna inserire anche tutti i dati della fattura-->
<section class="ftco-section ftco-cart">
	<div class="row ">
		<div class="card y x">
    
   <%@ include file = "UserSuggester.jsp" %>
 	 	
        <input type="date" class="ricerca"  name="da" placeholder="da data...">
 	    <input type="date" class="ricerca" name="a" placeholder="a data..." >
		<button class="button button2" id="search" style="border-radius:15px;">Cerca</button>

    	<div class="cart-list">
    	<h1>Ordini clienti</h1>
	    <%@ include file= "tableOrdersadmin.jsp" %>
		<p id = "pager" style = "text-align: center">pagina<input class = "pageof" type = "number" value = "1" min="1" max="<%= request.getAttribute("maxPg") %>"> di <%= request.getAttribute("maxPg") %> <button id = "submit" style="background-color: #1E90FF;border: none;color: white;border-radius:15px; width: 80px;height: 30px;">Invia</button></p>
	</div>
	</div>
	</div>
 </section>
