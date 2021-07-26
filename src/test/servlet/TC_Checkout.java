package test.servlet;

import coreModels.beans.*;
import coreModels.model.FatturaModel;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TC_Checkout {
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    FatturaModel fatturaDao;

    @Mock
    HttpSession session;

    @InjectMocks
    coreServlets.Checkout servlet;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCheckout1() throws Exception {//deve andare in thanks ma non va, per eccezione
       when(request.getSession()).thenReturn(session);
        when(session.getAttribute("isUser")).thenReturn(true);

        Cart c= new Cart();
        ProductBean prodotto= new ProductBean();
        prodotto.setCode(3);
        c.addOrder(new Order(prodotto,4));
        Map<Integer, Adress> addresses= new HashMap<>();
        when(session.getAttribute("cart")).thenReturn(c);
        when(request.getParameter("address")).thenReturn("2");
        when(session.getAttribute("addresses")).thenReturn(addresses);
        Registered e= new Registered();
        when(session.getAttribute("user")).thenReturn(e);

        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.doGet(request, response);
        verify(response).sendRedirect(captor.capture());
        assertEquals("Thanks.jsp",captor.getValue());
    }
    @Test
    public void testCheckout2() throws Exception {

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("isUser")).thenReturn(true);
        Cart c= new Cart();
        when(request.getSession().getAttribute("cart")).thenReturn(c);
        Map<Integer, Adress> addresses= new HashMap<>();
        when(request.getParameter("address")).thenReturn("2");
        when(request.getSession().getAttribute("addresses")).thenReturn(addresses);

        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.doGet(request, response);
        verify(response).encodeURL(captor.capture());
        assertEquals("error.jsp",captor.getValue());
    }
}
