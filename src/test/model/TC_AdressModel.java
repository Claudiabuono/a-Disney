package test.model;

import coreModels.beans.Admin;
import coreModels.beans.Adress;
import coreModels.beans.Registered;
import coreModels.model.AdressModel;
import coreModels.model.DM.AddressModelDM;
import coreModels.model.DM.AdminModelDM;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TC_AdressModel {
    AdressModel address;
    Registered utente;

    @BeforeEach
    void setUp() throws Exception{
        address = new AddressModelDM();
    }

    private Adress newAddress(){
         String street= "via cioffi";
         int nCv= 2;
         int CAP= 84069;
         String province= "Salerno";
         String citta= "Eboli";
        Adress address= new Adress(street, nCv, CAP, province, citta);
        return address;
    }

    private Adress getAddress(){
        String street= "via parmenide";
        int nCv= 1;
        int CAP= 84069;
        String province= "Salerno";
        String citta= "Roscigno";
        Adress address= new Adress(street, nCv, CAP, province, citta);
        return address;
    }

    private Registered getUtente(){
        String username= "matteo@alice.it";
        String password= "matteo";
        String nome= "matteo";
        String cognome= "russo";
        Registered utente= new Registered(nome,cognome,username,password);
        return utente;
    }
    @Test
    void doSave() throws SQLException{
        int i=address.doSave(newAddress(), getUtente());
        assertEquals(-1, i);

    }

    @Test
    void doDelete() throws SQLException{
        boolean b= address.doDelete(3);
        assertEquals(true, b);

    }

    @Test
    void doRetrieveAll() throws SQLException{
        address.doRetrieveAll("matteo@alice.it");
        Map<Integer, Adress> list= address.doRetrieveAll("matteo@alice.it");
        assertNotNull(list);
    }

    @Test
    void doModify() throws SQLException {
       boolean b= address.doModify(1, getAddress());
        assertEquals(true, b);
    }

    @Test
    void doRetrieve() throws SQLException {
        Adress a= address.doRetrieve(1);
        assertEquals(getAddress().toString(), a.toString());
    }
}
