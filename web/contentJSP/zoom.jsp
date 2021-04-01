<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div class="img-magnifier-container">
  <img id="myimage" src="${product.photo}">
</div>

<script type = "text/javascript" src = "js/magnifier.js"></script>
<script>magnify('myimage', 3);</script>