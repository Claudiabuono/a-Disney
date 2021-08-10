package test.integration;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.mail.Address;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coreModels.beans.Adress;
import coreModels.beans.Registered;
import coreModels.model.AdressModel;
import coreServlets.AddressOperations;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TC_AddressOperationsIntegration {
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @InjectMocks
    coreServlets.AddressOperations servlet;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    @Test //Op0
    public void testAddressOperation0() throws Exception {
        Map<Integer,Adress> ad= new HashMap<>();
        ad.put(1,new Adress("via roma", 5,67875,"Roma", "Roma"));
        Registered r= new Registered();

        when(request.getParameter("operation")).thenReturn("0");
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("user")).thenReturn(r);
        when(request.getSession().getAttribute("addresses")).thenReturn(ad);
        servlet.doGet(request, response);

        Map<Integer,Adress> valoreReale= (Map<Integer,Adress>) request.getSession().getAttribute("addresses");
        assertEquals(ad.toString(), valoreReale.toString());
    }
    @Test //Op1 TCS18
    public void testAddressOperation1_TCS18() throws Exception {
        Map<Integer,Adress> ad= new HashMap<>();
        ad.put(1,new Adress("via roma", 5,67875,"Roma", "Roma"));
        Registered r= new Registered();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("operation")).thenReturn("1");
        when(session.getAttribute("user")).thenReturn(r);
        when(session.getAttribute("addresses")).thenReturn(ad);
        when(request.getParameter("cap")).thenReturn("84");
        when(request.getParameter("citta")).thenReturn("Felitto");
        when(request.getParameter("stato")).thenReturn("italia");
        when(request.getParameter("ncv")).thenReturn("7");
        when(request.getParameter("provincia")).thenReturn("Salerno");
        when(request.getParameter("via")).thenReturn("via tommasini");

        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.doGet(request, response);
        verify(response).encodeURL(captor.capture());
        assertEquals("error.jsp",captor.getValue());
    }

    @Test //Op1 TCS21
    public void testAddressOperation1_TCS21() throws Exception {
        when(request.getParameter("operation")).thenReturn("1");
        when(request.getParameter("cap")).thenReturn("84061");
        when(request.getParameter("citta")).thenReturn("Felitto");
        when(request.getParameter("stato")).thenReturn("italia");
        when(request.getParameter("ncv")).thenReturn("7");
        when(request.getParameter("provincia")).thenReturn("Salerno");
        when(request.getParameter("via")).thenReturn("via tommasini");
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);

        String result = stringWriter.getBuffer().toString().trim();
        assertNotNull(result);
    }
    @Test //Op1 TCS21
    public void testAddressOperationDelete() throws Exception {
        when(request.getParameter("operation")).thenReturn("3");
        when(request.getSession()).thenReturn(session);

        servlet.doGet(request, response);
        AdressModel address= new AdressModel();
        Adress indirizzo= address.doRetrieve(3);
        assertEquals(null,indirizzo.getRegistrato());
    }
}

