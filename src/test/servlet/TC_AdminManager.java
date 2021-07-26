package test.servlet;


import coreModels.beans.FatturaBean;
import coreModels.beans.Registered;
import coreModels.model.FatturaModel;
import coreModels.model.Paginator;
import coreModels.model.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class TC_AdminManager {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    Paginator<FatturaBean> paginator;

    @Mock
    Pair<FatturaBean> pair1;

    @Mock
    RequestDispatcher rd;

    @Mock
    FatturaModel fatturaDao;

    @Mock
    ServletContext servletContext;


    @InjectMocks
    coreServlets.AdminManager servlet;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAdminManager() throws Exception
    {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("isAdmin")).thenReturn(true);
        when(request.getParameter("search")).thenReturn("rosalia@libero.it");
        when(request.getParameter("da")).thenReturn("2020-8-1");
        when(request.getParameter("a")).thenReturn("2020-8-4");
        when(request.getParameter("pg")).thenReturn("1");
        SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
        Date da= new Date(2020,8,1);
        Date a= new Date(2020,8,4);


        List<FatturaBean> list= new ArrayList<>();
        FatturaBean e= new FatturaBean();
        list.add(e);
        Registered r= new Registered();
        r.setLogin("rosalia@libero.it");
        when(fatturaDao.retrieveInvoices(da, a)).thenReturn(list);
        when(fatturaDao.retrieveInvoices(r, da, a)).thenReturn(list);
        when(paginator.paginate(list)).thenReturn(pair1);
        when(response.encodeURL("/contentJSP/tableOrdersadmin.jsp")).thenReturn("/contentJSP/tableOrdersadmin.jsp");

        when(servletContext.getRequestDispatcher("/contentJSP/tableOrdersadmin.jsp")).thenReturn(rd);

        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.doGet(request, response);
        verify(rd).forward(request, response);
        verify(response).encodeURL(captor.capture());
        assertEquals("/contentJSP/tableOrdersadmin.jsp",captor.getValue());
    }
}
