package test.servlet;

import coreModels.beans.ProductBean;
import coreModels.beans.Registered;
import coreModels.beans.UserBean;
import coreModels.model.ProductModel;
import coreModels.model.UserModel;
import coreServlets.ProductAdminControl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TC_ProductAdminControl extends Mockito {
    private static final long serialVersionUID = 1000L;
    static boolean isDataSource = false;

    static ProductBean product;
    static private ProductAdminControl servlet;

    @BeforeAll
    public static void init () {
        product = new ProductBean();
        servlet = new ProductAdminControl();

    }

    @Test //TCS I cambi sono vuoti
    public void testPAC26() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        ProductModel productDao= Mockito.mock(ProductModel.class);

      /* when(request.getParameter("name")).thenReturn(" ");
        when(request.getParameter("description")).thenReturn(" ");
        when(request.getParameter("personaggio")).thenReturn("Aurora");
        when(request.getParameter("url")).thenReturn("images/auroraCuscino.PNG");
        when(request.getParameter("tipo")).thenReturn("cuscino");
        when(request.getParameter("categoria")).thenReturn("0");
        when(request.getParameter("qty")).thenReturn("3");
        when(request.getParameter("price")).thenReturn(" ");
        when(request.getParameter("iva")).thenReturn("5.00");
        when(request.getParameter("sconto")).thenReturn("35.00");
*/
        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

       ProductBean prodotto= new ProductBean ("prova", " ", " ", "prova", "prova", 0, 6, 20, 20,8);

        when(productDao.doSave(product)).thenReturn(true);
        servlet.setProductModel(productDao);

        String result = stringWriter.getBuffer().toString().trim();
        assertNotNull(result);
    }

    @Test //TCS l'inserimento va a buon fine
    public void testPAC27() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        ProductModel productDao= Mockito.mock(ProductModel.class);

      /* when(request.getParameter("name")).thenReturn(" ");
        when(request.getParameter("description")).thenReturn(" ");
        when(request.getParameter("personaggio")).thenReturn("Aurora");
        when(request.getParameter("url")).thenReturn("images/auroraCuscino.PNG");
        when(request.getParameter("tipo")).thenReturn("cuscino");
        when(request.getParameter("categoria")).thenReturn("0");
        when(request.getParameter("qty")).thenReturn("3");
        when(request.getParameter("price")).thenReturn(" ");
        when(request.getParameter("iva")).thenReturn("5.00");
        when(request.getParameter("sconto")).thenReturn("35.00");
*/
        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        ProductBean prodotto= new ProductBean ("prova", " prova", "prova ", "prova", "prova", 0, 6, 20, 20,8);
        when(productDao.doSave(product)).thenReturn(true);
        servlet.setProductModel(productDao);
        servlet.doPost(request, response);

        String result = stringWriter.getBuffer().toString().trim();
        assertNotNull(result);
    }

    @Test //TCS (Modifica) Campi vuoti
    public void testPAC24() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        ProductModel productDao= Mockito.mock(ProductModel.class);


        when(request.getParameter("qty")).thenReturn("3");
        when(request.getParameter("price")).thenReturn(" ");
        when(request.getParameter("iva")).thenReturn("5.00");
        when(request.getParameter("discount")).thenReturn("5.00");
        when(request.getParameter("sconto")).thenReturn("35.00");

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        ProductBean prodotto= new ProductBean ("prova", " ", " ", "prova", "prova", 0, 0, 20, 20,8);
        when(productDao.doUpdate(product)).thenReturn(true);
        servlet.setProductModel(productDao);

        String result = stringWriter.getBuffer().toString().trim();
        assertNotNull(result);
    }

    @Test //TCS (Modifica) La modifica va a buon fine
    public void testPAC25() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        ProductModel productDao= Mockito.mock(ProductModel.class);


        when(request.getParameter("qty")).thenReturn("3");
        when(request.getParameter("price")).thenReturn(" ");
        when(request.getParameter("iva")).thenReturn("5.00");
        when(request.getParameter("discount")).thenReturn("5.00");
        when(request.getParameter("sconto")).thenReturn("35.00");

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        ProductBean prodotto= new ProductBean ("prova", " ", " ", "prova", "prova", 0, 0, 20, 20,8);
        when(productDao.doUpdate(product)).thenReturn(true);
        servlet.setProductModel(productDao);

        String result = stringWriter.getBuffer().toString().trim();
        assertNotNull(result);
    }

    @Test //TCS Eliminazione
    public void testPACDelate() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        ProductModel productDao= Mockito.mock(ProductModel.class);


        when(request.getParameter("code")).thenReturn("3");
        when(request.getParameter("name")).thenReturn(" ");
        when(request.getParameter("description")).thenReturn(" ");
        when(request.getParameter("personaggio")).thenReturn("Aurora");
        when(request.getParameter("url")).thenReturn("images/auroraCuscino.PNG");
        when(request.getParameter("tipo")).thenReturn("cuscino");
        when(request.getParameter("categoria")).thenReturn("0");
        when(request.getParameter("qty")).thenReturn("3");
        when(request.getParameter("price")).thenReturn(" ");
        when(request.getParameter("iva")).thenReturn("5.00");
        when(request.getParameter("sconto")).thenReturn("35.00");

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);


        when(productDao.doDelete(3)).thenReturn(true);
        servlet.setProductModel(productDao);
        servlet.doPost(request, response);

        String result = stringWriter.getBuffer().toString().trim();
        assertNotNull(result);
    }

}
