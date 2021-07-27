package test.servlet;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Address;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coreModels.beans.Adress;
import coreModels.beans.FatturaBean;
import coreModels.beans.Registered;
import coreModels.beans.UserBean;
import coreModels.model.AdressModel;
import coreModels.model.FatturaModel;
import coreModels.model.RegisteredModel;
import coreServlets.AddressOperations;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class TC_UserManager  {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    RegisteredModel registeredDao;

    @Mock
    FatturaModel fatturaDao;

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
        Registered userBean = new Registered("rosalia", "capozzolo","rosalia@libero.it" ,"rosalia" );
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("op")).thenReturn("modCred");
        when(request.getSession().getAttribute("user")).thenReturn(userBean);
        when(request.getParameter("nome")).thenReturn("rosalia");
        when(request.getParameter("cognome")).thenReturn("capozzolo");
        when(request.getParameter("login")).thenReturn("rosalia@libero.it");
        when(request.getParameter("pass")).thenReturn("rosalia");

        servlet.doPost(request, response);

        verify(registeredDao).doModify(userBean, "rosalia", "capozzolo","rosalia@libero.it", "rosalia" );

    }

    @Test
    public void test2() throws Exception {
        Registered userBean = new Registered("rosalia", "capozzolo","rosalia@libero.it" ,"rosalia" );
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("op")).thenReturn("viewFatture");
        when(request.getParameter("pg")).thenReturn("1");

        FatturaBean f=new FatturaBean();
        List<FatturaBean> list= new ArrayList<>();
        list.add(f);
        when(fatturaDao.retrieveInvoices(userBean, null, null)).thenReturn(list);
       when(response.encodeURL("/OrdiniUtente.jsp")).thenReturn("/OrdiniUtente.jsp");
       when(request.getRequestDispatcher("/OrdiniUtente.jsp")).thenReturn(rd);

        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.doGet(request, response);
        verify(response).encodeURL(captor.capture());
        assertEquals("/OrdiniUtente.jsp",captor.getValue());

    }

}
