package test.servlet;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coreModels.beans.Adress;
import coreModels.beans.Registered;
import coreModels.model.AdressModel;
import coreServlets.AddressOperations;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
public class TC_AddressOperations extends Mockito{
    static private AddressOperations servlet;
    @BeforeAll
    public static void init () {
        servlet = new AddressOperations();
    }
    @Test //Op0
    public void testAddressOperation0() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        AdressModel addressDao= Mockito.mock(AdressModel.class);

        when(request.getParameter("operation")).thenReturn("0");

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);

        String result = stringWriter.getBuffer().toString().trim();
        assertNotNull(result);
    }
    @Test //Op1 TCS18
    public void testAddressOperation1_TCS18() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        AdressModel addressDao= Mockito.mock(AdressModel.class);

        when(request.getParameter("operation")).thenReturn("1");
        when(request.getParameter("cap")).thenReturn("84");
        when(request.getParameter("citta")).thenReturn("Felitto");
        when(request.getParameter("stato")).thenReturn("italia");
        when(request.getParameter("ncv")).thenReturn("7");
        when(request.getParameter("provincia")).thenReturn("Salerno");
        when(request.getParameter("via")).thenReturn("via tommasini");

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        Adress bean = new Adress ("via tommasini", 7,84, "Salerno","Felitto");
        Registered user = new Registered("rosalia", "capozzolo","rosalia@libero.it" ,"rosalia" );
        when(addressDao.doSave(bean,  user));
        servlet.setAdressModel(addressDao);
        servlet.doGet(request, response);

        String result = stringWriter.getBuffer().toString().trim();
        assertNotNull(result);
    }
    @Test //Op1 TCS19
    public void testAddressOperation1_TCS19() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        AdressModel addressDao= Mockito.mock(AdressModel.class);

        when(request.getParameter("operation")).thenReturn("1");
        when(request.getParameter("cap")).thenReturn("84061");
        when(request.getParameter("citta")).thenReturn("Felitto6");
        when(request.getParameter("stato")).thenReturn("italia6");
        when(request.getParameter("ncv")).thenReturn("7");
        when(request.getParameter("provincia")).thenReturn("Salerno6");
        when(request.getParameter("via")).thenReturn("via tommasini@");

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        Adress bean = new Adress ("via tommasini@", 7,84061, "Salerno6","Felitto6");
        Registered user = new Registered("rosalia", "capozzolo","rosalia@libero.it" ,"rosalia" );
        when(addressDao.doSave(bean,  user));
        servlet.setAdressModel(addressDao);
        servlet.doGet(request, response);

        String result = stringWriter.getBuffer().toString().trim();
        assertNotNull(result);
    }
    @Test //Op1 TCS20
    public void testAddressOperation1_TCS20() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        AdressModel addressDao= Mockito.mock(AdressModel.class);

        when(request.getParameter("operation")).thenReturn("1");
        when(request.getParameter("cap")).thenReturn("");
        when(request.getParameter("citta")).thenReturn("");
        when(request.getParameter("stato")).thenReturn("");
        when(request.getParameter("ncv")).thenReturn("");
        when(request.getParameter("provincia")).thenReturn("");
        when(request.getParameter("via")).thenReturn("");

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        Adress bean = new Adress ("", 7,84061, "","");
        Registered user = new Registered("rosalia", "capozzolo","rosalia@libero.it" ,"rosalia" );
        when(addressDao.doSave(bean,  user));
        servlet.setAdressModel(addressDao);
        servlet.doGet(request, response);

        String result = stringWriter.getBuffer().toString().trim();
        assertNotNull(result);
    }
    @Test //Op1 TCS21
    public void testAddressOperation1_TCS21() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        AdressModel addressDao= Mockito.mock(AdressModel.class);

        when(request.getParameter("operation")).thenReturn("1");
        when(request.getParameter("cap")).thenReturn("84061");
        when(request.getParameter("citta")).thenReturn("Felitto");
        when(request.getParameter("stato")).thenReturn("italia");
        when(request.getParameter("ncv")).thenReturn("7");
        when(request.getParameter("provincia")).thenReturn("Salerno");
        when(request.getParameter("via")).thenReturn("via tommasini");

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        Adress bean = new Adress ("via tommasini", 7,84061, "Salerno","Felitto");
        Registered user = new Registered("rosalia", "capozzolo","rosalia@libero.it" ,"rosalia" );
        when(addressDao.doSave(bean,  user));
        servlet.setAdressModel(addressDao);
        servlet.doGet(request, response);

        String result = stringWriter.getBuffer().toString().trim();
        assertNotNull(result);
    }
    @Test //Op2
    public void testAddressOperation2() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        AdressModel addressDao= Mockito.mock(AdressModel.class);

        when(request.getParameter("operation")).thenReturn("1");
        when(request.getParameter("cap")).thenReturn("84061");
        when(request.getParameter("citta")).thenReturn("Felitto");
        when(request.getParameter("stato")).thenReturn("italia");
        when(request.getParameter("ncv")).thenReturn("7");
        when(request.getParameter("provincia")).thenReturn("Salerno");
        when(request.getParameter("via")).thenReturn("via tommasini");
        when(request.getParameter("code")).thenReturn("3");

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.setAdressModel(addressDao);
        servlet.doGet(request, response);

        String result = stringWriter.getBuffer().toString().trim();
        assertNotNull(result);
    }
}
