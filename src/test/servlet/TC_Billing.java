package test.servlet;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coreModels.beans.*;
import coreModels.model.UserModel;
import coreServlets.Billing;
import coreServlets.Login;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
public class TC_Billing extends Mockito{
    static private Billing servlet;

    @BeforeAll
    public static void init () {
       servlet = new Billing();
    }

    @Test
    public void testBilling1() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        Cart c= new Cart();
        ProductBean prodotto= new ProductBean();
        prodotto.setCode(3);
        c.addOrder(new Order(prodotto,4));
        when(request.getSession().getAttribute("cart")).thenReturn(c);

        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.doGet(request, response);
        verify(response).encodeURL(captor.capture());
        assertEquals("Login.jsp",captor.getValue());
    }

    @Test
    public void testBilling2() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        Cart c= new Cart();
        ProductBean prodotto= new ProductBean();
        prodotto.setCode(3);
        c.addOrder(new Order(prodotto,4));
        when(request.getSession().getAttribute("cart")).thenReturn(c);
        Registered r = new Registered();
        when(request.getSession().getAttribute("user")).thenReturn(r);

        RequestDispatcher rd1 = mock(RequestDispatcher.class);
        RequestDispatcher rd2 = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("AddressOperations?operation=0")).thenReturn(rd1);
        when(request.getRequestDispatcher(eq("Checkout.jsp"))).thenReturn(rd2);

        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.doGet(request, response);
        verify(response).encodeURL(captor.capture());
        assertEquals("Checkout.jsp",captor.getValue());
    }
}
