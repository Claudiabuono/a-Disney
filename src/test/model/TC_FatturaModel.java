package test.model;

import coreModels.beans.*;
import coreModels.connector.DriverManagerConnectionPool;
import coreModels.model.FatturaModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TC_FatturaModel {
    FatturaModel fatturaModel;


    @BeforeEach
    void setUp() throws Exception{
        fatturaModel = new FatturaModel();

    }

    private Registered getUtente(){
        String username= "matteo@alice.it";
        String password= "matteo";
        String nome= "matteo";
        String cognome= "russo";
        Registered utente= new Registered(nome,cognome,username,password);
        return utente;
    }

    private FatturaBean newFattura(){
       GregorianCalendar date= new GregorianCalendar();
       FatturaBean fattura= new FatturaBean();
       fattura.setDate(date);
       fattura.setUser(getUtente());
       fattura.setProdotti(getOrder());
       fattura.setShipping(getAddress());
        return fattura;
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

    private List<Order> getOrder(){
        Order order= new Order(getprodotto().get(0), 1);
        List<Order> o= new ArrayList<>();
        o.add(order);
        return o;
    }

    private FatturaBean getFattura(){
        GregorianCalendar date= new GregorianCalendar(2019,06,20);
        int cod= 1;
        FatturaBean fattura= new FatturaBean(getUtente(), date, cod);
        return fattura;
    }

    private List<ProductBean> getprodotto(){
        //name, description,personaggio, url, tipo,categoria,quantity,price,iva,sconto
        ProductBean prodotto= new ProductBean(1);
        List<ProductBean> l= new ArrayList<>();
        l.add(prodotto);
        return l;
    }

    private ProductBean getProdottoSingolo(){
        //name, description,personaggio, url, tipo,categoria,quantity,price,iva,sconto
        ProductBean prodotto= new ProductBean(2);
        return prodotto;
    }

    @Test
    void doSave() throws Exception{
        boolean b= fatturaModel.doSave(newFattura());
        assertEquals(true, b);
    }

    @Test
    void updateQtyProducts() throws java.sql.SQLException {
        Boolean b=fatturaModel.updateQtyProducts(getOrder(), DriverManagerConnectionPool.getConnection());
        assertEquals(true, b);
    }

    @Test
    void hasPurchased () throws SQLException {
        Boolean b= fatturaModel.hasPurchased(getProdottoSingolo(), getUtente());
        assertEquals(true, b);
    }

  /*  @Test
    void retrieveOrders () throws java.sql.SQLException {
        Date date = new Date(2019, 06, 20);
        Date date2= new Date(2021, 07, 19);
        List<Order> o= fatturaModel.retrieveOrders(getUtente(), date, date2);
        assertNotNull(o);
    }
*/
    @Test
    void  retrieveInvoiceOrders () throws java.sql.SQLException {
       List<Order> o= fatturaModel.retrieveInvoiceOrders(1, DriverManagerConnectionPool.getConnection());
        assertNotNull(o);
    }

    @Test
    void retrieveInvoice () throws java.sql.SQLException {
        FatturaBean f=fatturaModel.retrieveInvoice(getUtente(), 1);
        assertEquals(1, f.getCod());
    }

    @Test
    void retrieveInvoices () throws java.sql.SQLException {
        Date date = new Date(2019, 06, 20);
        Date date2= new Date(2021, 07, 19);
        List<FatturaBean> f= fatturaModel.retrieveInvoices(getUtente(),date, date2);
        assertNotNull(f);
    }

    @Test
    void retrieveInvoicesTest() throws SQLException {
        Date date = new Date(2019, 06, 20);
        Date date2= new Date(2021, 07, 19);
        List<FatturaBean> f= fatturaModel.retrieveInvoices(date, date2);
        assertNotNull(f);
    }

}
