package test.integration;


import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coreModels.beans.Registered;
import coreModels.model.RegisteredModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TC_UserManagerIntegration {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    RequestDispatcher rd;

    @InjectMocks
    coreServlets.UserManager servlet;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test //TCS5
    public void test1() throws Exception {
        Registered userBean= new Registered("rosalia", "capozzolo", "rosalia@libero.it", "rosalia");

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("op")).thenReturn("modCred");
        when(request.getSession().getAttribute("user")).thenReturn(userBean);
        when(request.getParameter("nome")).thenReturn("rosalia");
        when(request.getParameter("cognome")).thenReturn("capozzolo");
        when(request.getParameter("login")).thenReturn("rosalia@libero.it");
        when(request.getParameter("pass")).thenReturn("rosalia");

        servlet.doPost(request, response);
        RegisteredModel registeredDao2= new RegisteredModel();
        Registered registrato= registeredDao2.doRetrieveByKey("rosalia@libero.it");
        assertEquals("rosalia",registrato.getName());
        assertEquals("rosalia",registrato.getPassword());
        assertEquals("capozzolo",registrato.getCognome());
    }

    @Test
    public void test2() throws Exception {
        Registered userBean= new Registered();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("op")).thenReturn("viewFatture");
        when(request.getParameter("pg")).thenReturn("1");
        when(session.getAttribute("user")).thenReturn(userBean);
        when(response.encodeURL("/OrdiniUtente.jsp")).thenReturn("/OrdiniUtente.jsp");
        when(request.getRequestDispatcher("/OrdiniUtente.jsp")).thenReturn(rd);

        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.doGet(request, response);
        verify(response).encodeURL(captor.capture());
        assertEquals("/OrdiniUtente.jsp",captor.getValue());

    }

}
