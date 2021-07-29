package test.servlet;

import coreModels.beans.ProductBean;
import coreModels.model.ProductModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TC_ProductAdminControl {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    ProductModel model;

    @InjectMocks
    coreServlets.ProductAdminControl servlet;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test //TCS I cambi sono vuoti
    public void testPAC26() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("isAdmin")).thenReturn(true);
        when(request.getParameter("act")).thenReturn("insert");
        when(request.getParameter("name")).thenReturn("");
        when(request.getParameter("description")).thenReturn("");
        when(request.getParameter("personaggio")).thenReturn("Aurora");
        when(request.getParameter("url")).thenReturn("images/auroraCuscino.PNG");
        when(request.getParameter("tipo")).thenReturn("cuscino");
        when(request.getParameter("categoria")).thenReturn("0");
        when(request.getParameter("qty")).thenReturn("3");
        when(request.getParameter("price")).thenReturn("");
        when(request.getParameter("iva")).thenReturn("5.00");
        when(request.getParameter("sconto")).thenReturn("35.00");

        ArgumentCaptor<Integer> captor= ArgumentCaptor.forClass(Integer.class);
        servlet.doPost(request, response);
        verify(response).sendError(captor.capture());
        assertEquals(406,captor.getValue());
   }

    @Test //TCS l'inserimento va a buon fine
    public void testPAC27() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("isAdmin")).thenReturn(true);
        when(request.getParameter("act")).thenReturn("insert");
        when(request.getParameter("name")).thenReturn("Cuscino");
        when(request.getParameter("description")).thenReturn("Cuscino Aurora");
        when(request.getParameter("personaggio")).thenReturn("Aurora");
        when(request.getParameter("url")).thenReturn("images/auroraCuscino.PNG");
        when(request.getParameter("tipo")).thenReturn("cuscino");
        when(request.getParameter("categoria")).thenReturn("0");
        when(request.getParameter("qty")).thenReturn("3");
        when(request.getParameter("price")).thenReturn("7");
        when(request.getParameter("iva")).thenReturn("5");
        when(request.getParameter("sconto")).thenReturn("35");

        ProductBean prodotto= new ProductBean("Cuscino","Cuscino Aurora", "Aurora","images/auroraCuscino.PNG","cuscino", 0, 3, 7, 5, 35);

        servlet.doPost(request, response);
        verify(model).doSave(prodotto);

    }

    @Test //TCS (Modifica) Campi vuoti
    public void testPAC24() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("isAdmin")).thenReturn(true);
        when(request.getParameter("act")).thenReturn("modify");
        when(request.getParameter("qty")).thenReturn("");
        when(request.getParameter("price")).thenReturn("");
        when(request.getParameter("iva")).thenReturn("");
        when(request.getParameter("discount")).thenReturn("");
        when(request.getParameter("sconto")).thenReturn("35.00");

        ArgumentCaptor<Integer> captor= ArgumentCaptor.forClass(Integer.class);
        servlet.doPost(request, response);
        verify(response).sendError(captor.capture());
        assertEquals(406,captor.getValue());
   }

    @Test //TCS (Modifica) La modifica va a buon fine
    public void testPAC25() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("isAdmin")).thenReturn(true);
        when(request.getParameter("act")).thenReturn("modify");

        when(request.getParameter("qty")).thenReturn("3");
        when(request.getParameter("price")).thenReturn("4");
        when(request.getParameter("iva")).thenReturn("5.00");
        when(request.getParameter("discount")).thenReturn("5.00");
        when(request.getParameter("sconto")).thenReturn("35.00");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request,response);
        String result = stringWriter.getBuffer().toString().trim();
        assertNotNull(result);
    }

    @Test //TCS Eliminazione
    public void testPACDelate() throws Exception {

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("isAdmin")).thenReturn(true);
        when(request.getParameter("act")).thenReturn("delete");
        when(request.getParameter("id")).thenReturn("1");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);

        verify(model).doDelete(1);
    }

}
