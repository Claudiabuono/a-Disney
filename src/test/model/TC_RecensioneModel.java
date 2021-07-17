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


public class TC_RecensioneModel {
    RecensioneModel recensione;

    @BeforeEach
    void setUp() throws Exception{
        recensione = new RecensioneModelDM();
    }

    private ProductBean getprodotto(){
        ProductBean prodotto= new ProductBean (3);
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
        RecensioneBean oracolo= new RecensioneBean("bello", 5, "buono rossela");
        assertEquals(oracolo, recensioneBean);
    }

}
