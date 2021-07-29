package test.integration;

import coreModels.beans.ProductBean;
import coreModels.model.Paginator;
import coreModels.model.Pair;
import coreModels.model.ProductModel;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TC_AdminCatalogIntegration {
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    RequestDispatcher rd;

    @Mock
    ServletConfig sc;

    @InjectMocks
    coreServlets.AdminCatalog servlet;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testConsumerCatalog1() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("isAdmin")).thenReturn(true);
        when(request.getParameter("ctgy")).thenReturn("0");
        when(request.getParameter("srch")).thenReturn("");
        when(request.getParameter("pg")).thenReturn("1");
        when(response.encodeURL("catalogoAdm.jsp")).thenReturn("catalogoAdm.jsp");
        when(request.getRequestDispatcher("catalogoAdm.jsp")).thenReturn(rd);

        servlet.init(sc);
        servlet.doGet(request, response);
        verify(rd).forward(request, response);
        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        verify(response).encodeURL(captor.capture());
        assertEquals("catalogoAdm.jsp",captor.getValue());
    }

    @Test
    public void testConsumerCatalog2() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("isAdmin")).thenReturn(true);
        when(request.getParameter("ctgy")).thenReturn("");
        when(request.getParameter("srch")).thenReturn("Alice");
        when(request.getParameter("pg")).thenReturn("1");
       when(response.encodeURL("catalogoAdm.jsp")).thenReturn("catalogoAdm.jsp");
        when(request.getRequestDispatcher("catalogoAdm.jsp")).thenReturn(rd);

        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.init(sc);
        servlet.doGet(request, response);

        verify(rd).forward(request, response);
        verify(response).encodeURL(captor.capture());
        assertEquals("catalogoAdm.jsp",captor.getValue());
    }

    @Test
    public void testConsumerCatalog3() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("isAdmin")).thenReturn(true);
        when(request.getParameter("ctgy")).thenReturn("");
        when(request.getParameter("srch")).thenReturn("");
        when(request.getParameter("pg")).thenReturn("1");
        when(response.encodeURL("catalogoAdm.jsp")).thenReturn("catalogoAdm.jsp");

        when(request.getRequestDispatcher("catalogoAdm.jsp")).thenReturn(rd);
        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.init(sc);
        servlet.doGet(request, response);

        verify(rd).forward(request, response);
        verify(response).encodeURL(captor.capture());
        assertEquals("catalogoAdm.jsp",captor.getValue());
    }

    @Test
    public void testConsumerCatalog4() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("isAdmin")).thenReturn(false);

        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.init(sc);
        servlet.doGet(request, response);

        verify(response).sendRedirect(captor.capture());
        assertEquals("Login.jsp",captor.getValue());
    }
}
