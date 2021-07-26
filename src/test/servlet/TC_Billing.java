package test.servlet;

import coreModels.beans.Cart;
import coreModels.beans.Order;
import coreModels.beans.ProductBean;
import coreModels.beans.Registered;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
public class TC_Billing {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    RequestDispatcher rd1;

    @Mock
    RequestDispatcher rd2;

    @InjectMocks
    coreServlets.Billing servlet;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBilling1() throws Exception {
        when(request.getSession()).thenReturn(session);

        Cart c= new Cart();
        ProductBean prodotto= new ProductBean();
        prodotto.setCode(3);
        c.addOrder(new Order(prodotto,4));
        when(session.getAttribute("cart")).thenReturn(c);

        when(session.getAttribute("user")).thenReturn(null);
        when(response.encodeURL("Login.jsp")).thenReturn("Login.jsp");


        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.doGet(request, response);
        verify(response).encodeURL(captor.capture());
        assertEquals("Login.jsp",captor.getValue());
    }

    @Test
    public void testBilling2() throws Exception {
       when(request.getSession()).thenReturn(session);
        Cart c= new Cart();
        ProductBean prodotto= new ProductBean();
        prodotto.setCode(3);
        c.addOrder(new Order(prodotto,4));
        when(request.getSession().getAttribute("cart")).thenReturn(c);
        Registered r = new Registered();
        when(request.getSession().getAttribute("user")).thenReturn(r);

        when(request.getRequestDispatcher("AddressOperations?operation=0")).thenReturn(rd1);
        when(response.encodeURL("Checkout.jsp")).thenReturn("Checkout.jsp");
        when(request.getRequestDispatcher("Checkout.jsp")).thenReturn(rd2);

        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.doGet(request, response);
        verify(response).encodeURL(captor.capture());
        assertEquals("Checkout.jsp",captor.getValue());
    }
}
