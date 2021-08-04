package test.model;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import coreModels.beans.Registered;
import coreModels.beans.UserBean;
import coreModels.model.RegisteredModel;
import coreModels.model.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class TC_UserModel {
    UserModel userReg;

    @BeforeEach
    void setUp() throws Exception {
        userReg = new RegisteredModel();
    }

    private UserBean getUser(){
        UserBean utente= new Registered("rosalia", "capozzolo", "rosalia@libero.it", "rosalia");
        return utente;
    }

    @Test
    void testDoesExist() throws SQLException{
        Boolean flag= userReg.doesExist(getUser());
        assertEquals(true,flag);
    }
    @Test
    void testLogin () throws SQLException{
        UserBean utente= userReg.login("rosalia@libero.it", "rosalia");
        assertEquals("rosalia@libero.it",utente.getLogin());
    }

}
