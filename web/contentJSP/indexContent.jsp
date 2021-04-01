<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<img src="../../WebContent/images/disney.png" class = "homeImg">

 <section class="row">
  <section class="leftcolumn">
    <div class="card">
      <h3>Qui da noi troverai</h3>
      <%@include file = "slider.jsp"%>  
    </div>
    <div class="card" id = "catalog">
    </div>
  </section>
  <section class="rightcolumn" style="background-image: url('images/areg.gif');">
   
    <div class="card">
      <h3>Contatti</h3>
      <p>Per maggiori informazioni sui nostri prodotti, sulla modalità di consegna o altro contattare </p>
      	 	<h4>Email:</h4> 
      		<p> c.buono13@studenti.unisa.it <br> 
      		 m.milione3@studenti.unisa.it
      	 	</p>
      	 	<h4>Cellulare:</h4> 
      	 		<p>366 3808 &nbsp719 <br>
      	 	 	366 4422 &nbsp514</p>
      			  
      
    </div>
     <div class="card">
      <h3>Vieni a trovarci in negozio</h3>
      <iframe  id="video" width="853" height="480" src="https://www.youtube.com/embed/AJb6hA0tY-g" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
    </div>
     <div class="card">
     <h3>Mappa</h3>
     	<div class="google-maps">
  			<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3018.3558256323463!2d14.246764414908789!3d40.842110937583314!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x133b085ae2e3b221%3A0xc14546fe43f1aab8!2sDisney+Store!5e0!3m2!1sit!2sit!4v1560076344346!5m2!1sit!2sit" width="600" height="450" style="border:0" ></iframe>
		</div>
    </div>
    <div class="card">
      <h3>Social</h3>
      <div><img src="../../WebContent/images/facebook.png" width="20" height="20" > <span>Facebook</span></div>
   	  <div><img src="../../WebContent/images/instagram.png" width="20" height="20" > <span> Instagram</span></div>
    </div>
  </section>
</section>