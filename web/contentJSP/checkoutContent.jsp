<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	 <%@page import="coreModels.beans.*"%>
		<div class="field2">
    	<div class="a" style="padding: 1%;">
    	<%
	Boolean warning = (Boolean) request.getAttribute("warning");
	
	if (warning != null ? warning : false) {
		%>
		<div class="warning"><span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> <strong>Oops!</strong> La preghiamo di verificare che lei abbia un indirizzo di spedizione oppure che le quantita' dei prodotti voluti siano disponibili </div>
		
	<%}
%>
       	<h3>Billing Address</h3>
		  <%
        java.util.Map<Integer, coreModels.beans.Adress> map = (java.util.Map<Integer, coreModels.beans.Adress>) session.getAttribute("addresses");
       	java.util.List <coreModels.beans.Adress> addresses = map == null ? new java.util.ArrayList<coreModels.beans.Adress>() : new java.util.ArrayList (map.values());
       %> <label class="myLabel" for="fname"><i class="fa fa-user"></i>Nome: ${user.name}  ${user.cognome} </label>
		  <label class="myLabel" for="email"><i class="fa fa-envelope"></i> Email: ${user.login}</label>
		   <%@ include file = "newAddress.jsp" %>
		     <form action="checkout" method = "post">       			
       		     <label class="myLabel" for="adr"><i class="fa fa-address-card-o"></i> Address</label>
				 <select name="address" id=address>
          		  <% if (addresses.size() > 0) {
          		 		 for(Adress address : addresses) {%>
          		  			<option value="<%=address.getCodice()%>"><%=address%></option>
          		  		<%}
          		  	}%>
        		  </select>
        		  <%@ include file = "Acquista.jsp" %>        
	   
       		 	<input type="submit" value="Continue to checkout" class="btn">
        </form>   
     </div>
  </div>