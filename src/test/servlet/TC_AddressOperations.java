package test.servlet;
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

public class TC_AddressOperations {
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    AdressModel addressDao;

    @Mock
    Address a;

    @Mock
    Adress bean;

    @Mock
    Map<Integer,Adress> ad;

    @Mock
    Registered user;

    @Mock
    Registered r;

    @InjectMocks
    coreServlets.AddressOperations servlet;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test //Op0
    public void testAddressOperation0() throws Exception {
        when(request.getParameter("operation")).thenReturn("0");
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("user")).thenReturn(r);
        when(request.getSession().getAttribute("addresses")).thenReturn(ad);
        when(addressDao.doRetrieveAll("rosalia@libero.it")).thenReturn(ad);
        servlet.doGet(request, response);

        Map<Integer,Adress> valoreReale= (Map<Integer,Adress>) request.getSession().getAttribute("addresses");
        assertEquals(ad.toString(), valoreReale.toString());
 }
    @Test //Op1 TCS18
    public void testAddressOperation1_TCS18() throws Exception {
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
        when(addressDao.doSave(bean,  user)).thenReturn(1);

        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.doGet(request, response);
        verify(response).encodeURL(captor.capture());
        assertEquals("error.jsp",captor.getValue());
    }
    @Test //Op1 TCS19
    public void testAddressOperation1_TCS19() throws Exception {
        when(request.getParameter("operation")).thenReturn("1");
        when(request.getParameter("cap")).thenReturn("84061");
        when(request.getParameter("citta")).thenReturn("Felitto6");
        when(request.getParameter("stato")).thenReturn("italia6");
        when(request.getParameter("ncv")).thenReturn("7");
        when(request.getParameter("provincia")).thenReturn("Salerno6");
        when(request.getParameter("via")).thenReturn("via tommasini@");
        when(request.getSession()).thenReturn(session);
        when(addressDao.doSave(bean,  user));

        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.doGet(request, response);
        verify(response).encodeURL(captor.capture());
        assertEquals("error.jsp",captor.getValue());
    }
    @Test //Op1 TCS20
    public void testAddressOperation1_TCS20() throws Exception {
        when(request.getParameter("operation")).thenReturn("1");
        when(request.getParameter("cap")).thenReturn("");
        when(request.getParameter("citta")).thenReturn("");
        when(request.getParameter("stato")).thenReturn("");
        when(request.getParameter("ncv")).thenReturn("");
        when(request.getParameter("provincia")).thenReturn("");
        when(request.getParameter("via")).thenReturn("");
        when(request.getSession()).thenReturn(session);
        when(addressDao.doSave(bean,  user));

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

        when(addressDao.doSave(bean,  user));
        servlet.doGet(request, response);

        String result = stringWriter.getBuffer().toString().trim();
        assertNotNull(result);
    }
    /*@Test //Op2
    public void testAddressOperation2() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        AdressModel addressDao= Mockito.mock(AdressModel.class);
        Adress a= new Adress("via tommasini", 7,84061, "Salerno", "Felitto");
        a.setNation("italia");
        a.setCodice(3);
        when(request.getParameter("operation")).thenReturn("2");
        when(request.getParameter("cap")).thenReturn("84061");
        when(request.getParameter("citta")).thenReturn("Felitto");
        when(request.getParameter("stato")).thenReturn("italia");
        when(request.getParameter("ncv")).thenReturn("7");
        when(request.getParameter("provincia")).thenReturn("Salerno");
        when(request.getParameter("via")).thenReturn("via tommasinSalernoi");
        when(request.getParameter("code")).thenReturn("3");
         HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(addressDao.doModify(3, a)).thenReturn(true);

        servlet.setAdressModel(addressDao);


        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.doGet(request,response);
        Boolean flag= (Boolean) session.getAttribute("flagModifica");
        assertEquals(true,flag);
    }*/
}
