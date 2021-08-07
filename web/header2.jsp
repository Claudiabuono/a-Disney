<%@page import="coreModels.beans.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>
<%
    Boolean isAdmin = session.getAttribute("isAdmin") == null ? false : (Boolean) session.getAttribute("isAdmin");
    Boolean isUser = session.getAttribute("isUser") == null ? false : (Boolean) session.getAttribute("isUser");
%>
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>                        
      </button>
        <% if(isAdmin){ %>
            <a class="navbar-brand" href="amministratore.jsp">DisneyStore</a>
        <% } else{ %>
            <a class="navbar-brand" href="index.jsp">DisneyStore</a>
        <% } %>

    </div>

          <div>

      <div class="collapse navbar-collapse" id="myNavbar">
          <% if(!isAdmin){ %>
        <ul class="nav navbar-nav">

                 <li><a href="carrello.jsp">Carrello</a></li>

           <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Prodotti <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="catalogo?ctgy=0&pg=1">Casa</a></li>
              <li><a href="catalogo?ctgy=1&pg=1">Moda</a></li>
              <li><a href="catalogo?ctgy=2&pg=1">Articoli Festa</a></li>
              <li><a href="catalogo?ctgy=3&pg=1">Giochi</a></li>
            </ul>
          </li>
        </ul> <% } %>
        <ul class="nav navbar-nav navbar-right">
            <%//AddressOperations?operation=0&pag=0
        if (isUser) {%>
            <li><%@ include file="contentJSP/ricerca.jsp" %></li>
        	<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><span> ${user.name}</span><span class="caret"></span></a>
            	<ul class="dropdown-menu">
            	<li><a href="visIndirizzi">Area Personale</a>
            	<li><a href="UserManager?op=viewFatture">Visualizza Ordini</a></li>
        		<li><a href="Logout">Logout</a></li>
        		</ul>
        <%} else if (isAdmin) {%>
                <li><a href="admin">Ordini clienti</a> </li>
                <li><a href="admincat">Revisiona catalogo</a> </li>
                <li><a href="AddProdottiAdm.jsp">Aggiungi prodotti</a> </li>
                <li><a href="Logout">Logout</a></li>
             <% }
        else { %>
        	<li><%@ include file="contentJSP/ricerca.jsp" %></li>
      		<li class="loginClear"><a href="Login.jsp"><span class="glyphicon glyphicon-log-in"></span>&#160 Login</a></li>
      		<li><a href="registration.jsp"><span class="glyphicon glyphicon-user"></span>&#160 Iscriviti</a></li>
      	<%}%>
      	
    	</ul>
      </div>
    </div>
  </div>
</nav>  