package test.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import coreModels.beans.Registered;
import coreModels.model.RegisteredModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.*;
public class TC_RegisteredModel {
    RegisteredModel registrato;

    @BeforeEach
    void setUp() throws Exception{
        registrato = new RegisteredModel();
    }

    Registered getUtente(){
        Registered registrato= new Registered("Leo", "Fulgione", "aleo8@gmail.com","leofulgione");
        return registrato;
    }
    @Test
    void testDoRetrieveByKey() throws SQLException{
        Registered registered = registrato.doRetrieveByKey("rosalia@libero.it");
        assertEquals("rosalia@libero.it", registered.getLogin());
    }

    @Test
    void testRegistration()throws SQLException{
        Boolean flag= registrato.registration(getUtente());
        assertEquals(true, flag);
    }

    @Test
    void testDoModify()throws SQLException{
        Boolean flag= registrato.doModify(getUtente(),"leo", "fulgione", "bleo@gmail.com","leofulgione");
        assertEquals(true, flag);
    }
    @Test
    void testDoRetrieveBySearch() throws SQLException{
        ArrayList<Registered> listaRegistrati= registrato.doRetrieveBySearch("anna@virgilio.it");
        assertNotNull(listaRegistrati);
    }




}
