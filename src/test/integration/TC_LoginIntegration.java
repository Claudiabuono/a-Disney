package test.integration;

import java.io.PrintWriter;
import java.io.StringWriter;

import coreModels.beans.UserBean;
import coreModels.model.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
public class TC_LoginIntegration {
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    RequestDispatcher rd;

    @InjectMocks
    coreServlets.Login servlet;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test //TCS1
    public void testLogin1() throws Exception {
        when(request.getParameter("username")).thenReturn("rosa@libero.it");
        when(request.getParameter("password")).thenReturn("rosa1234");
        when(request.getSession()).thenReturn(session);

        assertThrows(Exception.class, ()->servlet.doPost(request, response));
    }

    @Test //TCS2
    public void testLogin2() throws Exception {
        when(request.getParameter("username")).thenReturn("");
        when(request.getParameter("password")).thenReturn("rosa1234");
        when(request.getSession()).thenReturn(session);

        assertThrows(Exception.class, ()->servlet.doPost(request, response));
    }

    @Test //TCS3
    public void testLogin3() throws Exception {
        when(request.getParameter("username")).thenReturn("rosa@libero.it");
        when(request.getParameter("password")).thenReturn("");
        when(request.getSession()).thenReturn(session);
        when(response.encodeURL("/Login.jsp")).thenReturn("/Login.jsp");
        when(request.getRequestDispatcher("/Login.jsp")).thenReturn(rd);

        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.doPost(request, response);
        verify(response).encodeURL(captor.capture());
        assertEquals("/Login.jsp",captor.getValue());
    }

    @Test //TCS4
    public void testLogin4() throws Exception {
        when(request.getParameter("username")).thenReturn("rosalia");
        when(request.getParameter("password")).thenReturn("rosalia");
        when(request.getSession()).thenReturn(session);
        when(response.encodeURL("/Login.jsp")).thenReturn("/Login.jsp");
        when(request.getRequestDispatcher("/Login.jsp")).thenReturn(rd);

        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.doPost(request, response);
        verify(response).encodeURL(captor.capture());
        assertEquals("/Login.jsp",captor.getValue());
    }

    @Test //TCS5
    public void testLogin5() throws Exception {
        when(request.getParameter("username")).thenReturn("rosalia@libero.it");
        when(request.getParameter("password")).thenReturn("rosalia");
        when(request.getSession()).thenReturn(session);

        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.doPost(request, response);
        verify(response).encodeURL(captor.capture());
        assertEquals("index.jsp",captor.getValue());
    }
}
