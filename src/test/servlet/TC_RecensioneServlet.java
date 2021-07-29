package test.servlet;

import coreModels.beans.ProductBean;
import coreModels.beans.RecensioneBean;
import coreModels.beans.Registered;
import coreModels.model.*;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TC_RecensioneServlet {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    RecensioneModel recensioneDao;

    @Mock
    FatturaModel fatturaDao;

    @Mock
    UserModel userDao;

    @Mock
    Registered r;

    @Mock
    ProductBean b;

    @Mock
    List<RecensioneBean> list;

    @Mock
    RecensioneBean recensioneBean;

    @InjectMocks
    RecensioneServlet servlet;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test //TCS
    public void testRecensioneView() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("act")).thenReturn("view");
        when(request.getAttribute("product")).thenReturn(b);
        when(request.getSession().getAttribute("user")).thenReturn(r);
        when(recensioneDao.getComments(b)).thenReturn(list);
        when(recensioneDao.userComment(r, b)).thenReturn(recensioneBean);
        when(fatturaDao.hasPurchased(b, r)).thenReturn(true);
        when(recensioneDao.mediumVote(b)).thenReturn(4.5);

        servlet.doGet(request,response);
        verify(request).setAttribute("payed", false);
    }

    @Test
    public void testRecensione2() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("act")).thenReturn("insert");
        when(request.getAttribute("product")).thenReturn(b);
        when(session.getAttribute("user")).thenReturn(r);
        when(request.getParameter("vote")).thenReturn("2");
        when(request.getParameter("comment")).thenReturn("Prodotto curato nei minimi dettagli");
        when(request.getParameter("id")).thenReturn("3");
        when(r.getLogin()).thenReturn("annacuore1@ciao.it");

        servlet.doPost(request, response);

        //verify(recensioneDao).newComment(r,b, "Prodotto curato nei minimi dettagli",3);

        assertTrue(recensioneDao.newComment(r,b, "Prodotto curato nei minimi dettagli",3));
    }

}
