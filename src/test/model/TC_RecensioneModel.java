package test.model;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import coreModels.beans.ProductBean;
import coreModels.beans.RecensioneBean;
import coreModels.beans.Registered;
import coreModels.model.DM.RecensioneModelDM;
import coreModels.model.RecensioneModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TC_RecensioneModel {
    RecensioneModel recensione;

    @BeforeEach
    void setUp() throws Exception{
        recensione = new RecensioneModelDM();
    }

    private ProductBean getprodotto(){
        String nome= "Cuscino Tamburello";
        String descrizione= "Cuscino di stoffa riempito con morbida lana, tale da garandire comfort e relax";
        String personaggio= "Bambi";
        String url= "images/coniglioCuscino.PNG";
        String tipo="cuscino";
        int categoria=0;
        int quantity= 0;
        double price= 18.00;
        double iva=5.00;
        double sconto=35.00;
        ProductBean prodotto= new ProductBean (nome,descrizione,personaggio,url,tipo,categoria,quantity,price,iva,sconto);
        return prodotto;
    }
    private Registered getUtente(){
        String username= "rossella@alice.it";
        String password= "rossellabuono";
        String nome= "rossela";
        String cognome= "buono";
        Registered utente= new Registered(nome,cognome,username,password);
        return utente;
    }
    @Test
    void testMediumVote () throws SQLException {
        double media= recensione.mediumVote(getprodotto());
        assertEquals(4.5, media);
    }
    @Test
    void testUserComment()throws SQLException {
        RecensioneBean recensioneBean= recensione.userComment(getUtente(),getprodotto());
        RecensioneBean oracolo= new RecensioneBean("bello", 5, "rossella@alice.it");
        assertEquals(oracolo, recensioneBean);
    }
  //  public synchronized RecenzioneBean userComment (coreModels.beans.Registered e, coreModels.beans.ProductBean p) throws SQLException {
}
