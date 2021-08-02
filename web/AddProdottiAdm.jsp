<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 
Boolean admin= (Boolean)session.getAttribute("isAdmin");
if(admin == null ? true : !admin.booleanValue()) {
	response.sendRedirect(response.encodeURL("Login.jsp"));
	return;
}
 %>
<!DOCTYPE html >
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="css/stile.css" type = "text/css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
 		
	<title>Nuovi prodotti</title>
</head>
<body style="background-image: url('images/areg.gif');">
	<div class="field2"><a href="Logout">Logout</a>
		<%--@declare id="fname"--%><%--@declare id="personaggio"--%><h3>Aggiungi un nuovo prodotto </h3><br>
 		<label  class="myLabel" for="fname"> Nome prodotto</label>
    	<input class="myInput1" type="text" id="nome" name="nome" placeholder="Cuscino Aurora" >
    	     	 
    	<label  class="myLabel" for="prezzo"> Prezzo</label>
    	<input class="myInput1" type="number" id="prezzo" name="prezzo"  placeholder="17.90" >
    	
    	<label  class="myLabel" for="quantita"> Quantità</label>
    	<input class="myInput1" type="number" id="quantita" name="quantita"  min="1">
    	
    	<label  class="myLabel" for="personaggio"> Personaggio</label>
    	<input class="myInput1" type="text" id="prs" name="personaggio"  placeholder="Aurora">
    	
 		<label  class="myLabel" for="url">Url Foto</label>
    	<input class="myInput1" type="url" id="url" name="url"  placeholder="images/auroraCuscino.PNG">
    	
 		<label  class="myLabel" for="iva">Iva</label>
    	<input class="myInput1" type="number" id="iva" name="iva"  placeholder="5">
    	
    	<label  class="myLabel" for="sconto">Sconto</label>
    	<input class="myInput1" type="number" id="sconto" name="sconto"  placeholder="10, in mancanza inserire 0">
    	
    	<label  class="myLabel" for="tipo">Tipo</label>
    	<input class="myInput1" type="text" id="tipo" name="tipo"  placeholder="cuscino">
    	
    	<label  class="myLabel" for="tipo">Categoria</label>
    	<select class="myInput1" type="text" id="categoria" name="categoria" >
    		<option value="0">Articoli per la Casa</option>
  			<option value="1">Articoli di Abbigliamento</option>
 		 	<option value="2">Articoli per le Feste</option>
  			<option value="3">Giochi</option>
    	</select>
    	
    	<label  class="myLabel" for="tipo">Descrizione</label>
    	<textarea class="myInput1" type="text" id="des" name="des"  placeholder="Inserire descrizione prodotto" rows="9"></textarea>
    	
    	<button id="but1" class=" button button2">Aggiungi</button>
    	<button id="but2" class=" button button2" href="amministratore.jsp">Indietro</button>
    	<div id="esito"></div>
   	    <hr>
	</div>
<script type="text/javascript" src= "js/addProdotti.js"></script>

</body>
</html>