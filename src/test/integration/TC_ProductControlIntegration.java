package test.integration;


import coreModels.beans.Cart;
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
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class TC_ProductControlIntegration {
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    RequestDispatcher rd;


    @InjectMocks
    coreServlets.ProductControl servlet;


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test //TCS
    public void testAddC() throws Exception {
        Cart c= new Cart();
        when(request.getSession()).thenReturn(session);
        when((Cart)request.getSession().getAttribute("cart")).thenReturn(c);
        when(request.getParameter("act")).thenReturn("addC");
        when(request.getParameter("id")).thenReturn("3");
        when(request.getParameter("qty")).thenReturn("2");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);


        servlet.doGet(request, response);

        String result = stringWriter.getBuffer().toString().trim();
        assertNotNull(result);

    }

    @Test //TCS
    public void testView() throws Exception {
        Cart c= new Cart();
        when(request.getSession()).thenReturn(session);
        when((Cart)request.getSession().getAttribute("cart")).thenReturn(c);
        when(request.getParameter("act")).thenReturn("view");
        when(request.getParameter("id")).thenReturn("3");
        when(response.encodeURL("RecenzioneServlet?act=view")).thenReturn("RecenzioneServlet?act=view");
        when(response.encodeURL("/productView.jsp")).thenReturn("/productView.jsp");
        when(request.getRequestDispatcher("/productView.jsp")).thenReturn(rd);
        when(request.getRequestDispatcher("RecenzioneServlet?act=view")).thenReturn(rd);
        servlet.doGet(request, response);

        verify(rd).forward(request, response);
    }

    @Test //TCS
    public void testDelete() throws Exception {
        Cart c= new Cart();
        when(request.getSession()).thenReturn(session);
        when((Cart)request.getSession().getAttribute("cart")).thenReturn(c);
        when(request.getParameter("act")).thenReturn("delete");
        when(request.getParameter("id")).thenReturn("3");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);

        String result = stringWriter.getBuffer().toString().trim();
        assertNotNull(result);
    }
}
