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
import java.util.*;


public class TC_RecensioneModel {
    RecensioneModel recensione;

    @BeforeEach
    void setUp() throws Exception{
        recensione = new RecensioneModelDM();
    }

    private ProductBean getprodotto(){
        ProductBean prodotto= new ProductBean (10);
        return prodotto;
    }
    private Registered getUtente(){
        String username= "rosalia@libero.it";
        String password= "rosalia";
        String nome= "rosalia";
        String cognome= "capozzolo";
        Registered utente= new Registered(nome,cognome,username,password);
        return utente;
    }
    @Test
    void testMediumVote () throws SQLException {
        double media= recensione.mediumVote(getprodotto());
        assertEquals(4.0, media);
    }
    @Test
    void testUserComment()throws SQLException {
        RecensioneBean recensioneBean= recensione.userComment(getUtente(),getprodotto());
        RecensioneBean oracolo= new RecensioneBean("Prodotto curato nei minimi dettagli", 4, "capozzolo rosalia");
        assertEquals(oracolo, recensioneBean);
    }

    @Test
    void testGetComments()throws SQLException{
        List<RecensioneBean> listRecensione= recensione.getComments (getprodotto());
        List<RecensioneBean> oracolo= new ArrayList<>();
        RecensioneBean r1= new RecensioneBean("Buona qualità e consegna in tempo", 4, "russo matteo");
        RecensioneBean r2= new RecensioneBean("Prodotto curato nei minimi dettagli", 4, "capozzolo rosalia");
        oracolo.add(r1);
        oracolo.add(r2);
        assertEquals(oracolo, listRecensione);
    }

}
