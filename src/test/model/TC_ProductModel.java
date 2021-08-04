package test.model;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import coreModels.beans.ProductBean;
import coreModels.model.ProductModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.*;
public class TC_ProductModel {
    ProductModel product;

    @BeforeEach
    void setUp() throws Exception{
        product = new ProductModel();
    }

    private ProductBean getprodotto(){
        //name, description,personaggio, url, tipo,categoria,quantity,price,iva,sconto
        ProductBean prodotto= new ProductBean ("prova", "prova", "prova", "prova", "prova", 0, 6, 20, 20,8);
        return prodotto;
    }

    @Test
    void testDoRetrieveByKey() throws SQLException{
        ProductBean prodotto= product.doRetrieveByKey(2,true);
        assertEquals(2, prodotto.getCode());
    }

    @Test
    void testDoSave() throws SQLException{
        Boolean flag = product.doSave(getprodotto());
        assertEquals(true, flag);
    }



    @Test
    void testDoRetrieveAll() throws SQLException{
        List<ProductBean> listaPodotti= product.doRetrieveAll(true);
        assertNotNull(listaPodotti);
    }

    @Test
    void testDoRetrieveByCategory() throws SQLException{
        List<ProductBean> listaPodotti= product.doRetrieveByCategory(0);
        assertNotNull(listaPodotti);
    }

    @Test
    void testDoRetrieveBySearch() throws SQLException{
        List<ProductBean> listaPodotti= product.doRetrieveBySearch("a",true);
        assertNotNull(listaPodotti);
    }

    @Test
    void testDoRetrieveByDiscount() throws Exception {
        List<ProductBean> listaPodotti= product.doRetrieveByDiscount(10.00,true);
        assertNotNull(listaPodotti);
    }

    @Test
    void testDoUpdate() throws SQLException{
        ProductBean prodotto= new ProductBean(60);
        prodotto.setDiscount(99.99);
        prodotto.setIva(99.99);
        prodotto.setPrice(99.99);
        prodotto.setQty(99);
        Boolean flag = product.doUpdate(prodotto);
        assertEquals(true, flag);
    }

    @Test
    void testDoRetrieveList () throws SQLException{
        int[] code= new int[1];
        code[0]= 1;
        List<ProductBean> listaprodotti= product.doRetrieveList(code,true);
        assertEquals(1, listaprodotti.get(0).getCode());
    }

    @Test
    void testDoDelete() throws SQLException{
      Boolean flag= product.doDelete(60);
      assertEquals(true, flag);
    }
}
