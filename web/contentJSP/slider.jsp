<div class="container">
<!-- lo slider ha bisogno di un qualsiasi id per funzionare -->
  <div id="general" class="carousel slide" data-ride="carousel">
   	 <!-- l'id viene usato per linkare n elementi allo slider -->
    <ol class="carousel-indicators">
      <li data-target="#general" data-slide-to="0" class="active"></li>
      <li data-target="#general" data-slide-to="1"></li>
      <li data-target="#general" data-slide-to="2"></li>
      <li data-target="#general" data-slide-to="3"></li>
      <li data-target="#general" data-slide-to="4"></li>
      <li data-target="#general" data-slide-to="5"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner">

      <div class="item active">
        <img src="images/Peluche.PNG" alt="Peluche" style="width:1000px; height:auto">
        <div class="carousel-caption">
          <h3>Peluche</h3>
          <p>I peluche pi? morbidi e pi? coccolosi di Topolino, Mulan, Aladdin e Anna di Frozen.</p>
        </div>
      </div>

      <div class="item">
        <img src="images/Barbie.PNG" alt="Barbie" style="width:1000px; height:auto">
        <div class="carousel-caption">
          <h3>Bambole</h3>
          <p>Storie e momenti di gioco spensierati con la bellissima collezione di bambole Disney!</p>
        </div>
      </div>
      
      <div class="item">
        <img src="images/Tazze.PNG" alt="Tazze" style="width:1000px; height:auto">
        <div class="carousel-caption">
          <h3>Tazze</h3>
         
        </div>
      </div>
    
      <div class="item">
        <img src="images/Bambini.PNG" alt="Bambini" style="width:1000px; height:auto" >
        <div class="carousel-caption">
          <h3>Abbigliamento Bambini</h3>
        </div>
      </div>
      
       <div class="item">
        <img src="images/Donne.PNG" alt="Donne" style="width:1000px; height:auto">
        <div class="carousel-caption">
          <h3>Abbigliamento Donna</h3>
        </div>
      </div>
      
       <div class="item">
        <img src="images/Uomo.PNG" alt="Uomo" style="width:1000px; height:auto">
        <div class="carousel-caption">
          <h3>Abbigliamento Uomo</h3>
        </div>
      </div>
  
    </div>

    <!-- bottoni per andare a destra e a sinistra -->
    <a class="left carousel-control" href="#general" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#general" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</div>