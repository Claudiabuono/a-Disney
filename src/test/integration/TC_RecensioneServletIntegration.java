package test.integration;

import coreModels.beans.ProductBean;
import coreModels.beans.RecensioneBean;
import coreModels.beans.Registered;
import coreModels.model.*;
import coreModels.model.DM.RecensioneModelDM;
import coreServlets.RecensioneServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TC_RecensioneServletIntegration {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @InjectMocks
    RecensioneServlet servlet;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test //TCS
    public void testRecensioneView() throws Exception {
        ProductBean b= new ProductBean();
        Registered r= new Registered();
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("act")).thenReturn("view");
        when(request.getAttribute("product")).thenReturn(b);
        when(request.getSession().getAttribute("user")).thenReturn(r);


        servlet.doGet(request,response);
        verify(request).setAttribute("payed", false);
    }

    @Test
    public void testRecensioneInsert() throws Exception {
        ProductBean b= new ProductBean();
        Registered r= new Registered();
        r.setLogin("andrea@libero.it");
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("act")).thenReturn("insert");
        when(request.getAttribute("product")).thenReturn(b);
        when(session.getAttribute("user")).thenReturn(r);
        when(request.getParameter("vote")).thenReturn("2");
        when(request.getParameter("comment")).thenReturn("Prodotto curato nei minimi dettagli");
        when(request.getParameter("id")).thenReturn("3");

        servlet.doPost(request, response);
        String oracolo="RecenzioneBean [description=Prodotto curato nei minimi dettagli, valutazione=2.0, name=caputo andrea]";
        Registered registered= new Registered();
        registered.setLogin("andrea@libero.it");
        registered.setName("andrea");
        registered.setCognome("caputo");
        ProductBean productBean= new ProductBean(3);
        RecensioneModel recensioneDao2= new RecensioneModelDM();
        RecensioneBean recensione=  recensioneDao2.userComment(registered,productBean);
        assertEquals(oracolo, recensione.toString());
    }
}
