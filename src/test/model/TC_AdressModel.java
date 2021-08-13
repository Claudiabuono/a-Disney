package test.model;

import coreModels.beans.Address;
import coreModels.beans.Registered;
import coreModels.model.AddressModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TC_AdressModel {
    AddressModel address;
    Registered utente;

    @BeforeEach
    void setUp() throws Exception{
        address = new AddressModel();
    }

    private Address newAddress(){
         String street= "via cioffi";
         int nCv= 2;
         int CAP= 84069;
         String province= "Salerno";
         String citta= "Eboli";
        Address address= new Address(street, nCv, CAP, province, citta);
        return address;
    }

    private Address getAddress(){
        String street= "via parmenide";
        int nCv= 1;
        int CAP= 84069;
        String province= "Salerno";
        String citta= "Roscigno";
        Address address= new Address(street, nCv, CAP, province, citta);
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
        Boolean flag= address.doDelete(3);
        assertEquals(true, flag);
    }


    @Test
    void doRetrieveAll() throws SQLException{
        address.doRetrieveAll("matteo@alice.it");
        Map<Integer, Address> list= address.doRetrieveAll("matteo@alice.it");
        assertNotNull(list);
    }

    @Test
    void doRetrieve() throws SQLException {
        Address a= address.doRetrieve(1);
        assertEquals(getAddress().toString(), a.toString());
    }
}
