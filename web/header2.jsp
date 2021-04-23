<%@page import="coreModels.beans.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>
	
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="index.jsp">DisneyStore</a>
    </div>
    <div>
      <div class="collapse navbar-collapse" id="myNavbar">
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
        </ul>
        <ul class="nav navbar-nav navbar-right">
        <%
        Boolean isAdmin = session.getAttribute("isAdmin") == null ? false : (Boolean) session.getAttribute("isAdmin");
        Boolean isUser = session.getAttribute("isUser") == null ? false : (Boolean) session.getAttribute("isUser");
        
        if (isUser) {%>
            <li><%@ include file="contentJSP/ricerca.jsp" %></li>
        	<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><span>Welcome ${user.name}</span><span class="caret"></span></a>
            	<ul class="dropdown-menu">
            	<li><a href="protected-page.jsp">Area Personale</a>
            	<li><a href="UserManager?op=viewFatture">Visualizza Ordini</a></li>
        		<li><a href="Logout">Logout</a></li>
        		</ul>
        <%} else if (isAdmin) {%>
        	<li><a href="#"><span>Welcome </span>${user.name}</a></li>
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