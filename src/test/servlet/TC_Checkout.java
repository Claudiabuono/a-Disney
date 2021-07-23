package test.servlet;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coreModels.beans.Adress;
import coreModels.beans.Cart;
import coreModels.beans.Order;
import coreModels.beans.ProductBean;
import coreModels.model.FatturaModel;
import coreServlets.Checkout;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
public class TC_Checkout extends Mockito{
    static private Checkout servlet;

    @BeforeAll
    public static void init () {
       servlet = new Checkout();
    }

    @Test
    public void testCheckout1() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        FatturaModel fatturaDao= Mockito.mock(FatturaModel.class);

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        Cart c= new Cart();
        ProductBean prodotto= new ProductBean();
        prodotto.setCode(3);
        c.addOrder(new Order(prodotto,4));
        Map<Integer, Adress> addresses= new HashMap<>();
        when(request.getSession().getAttribute("cart")).thenReturn(c);
        when(request.getParameter("address")).thenReturn("2");
        when(request.getSession().getAttribute("addresses")).thenReturn(addresses);

         servlet.setFatturaModel(fatturaDao);

        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.doGet(request, response);
        verify(response).sendRedirect(captor.capture());
        assertEquals("Thanks.jsp",captor.getValue());
    }
    @Test
    public void testCheckout2() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        FatturaModel fatturaDao= Mockito.mock(FatturaModel.class);

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        Cart c= new Cart();
        when(request.getSession().getAttribute("cart")).thenReturn(c);
        Map<Integer, Adress> addresses= new HashMap<>();
        when(request.getParameter("address")).thenReturn("2");
        when(request.getSession().getAttribute("addresses")).thenReturn(addresses);

        servlet.setFatturaModel(fatturaDao);

        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.doGet(request, response);
        verify(response).encodeURL(captor.capture());
        assertEquals("error.jsp",captor.getValue());
    }
}
