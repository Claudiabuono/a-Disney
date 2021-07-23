package test.servlet;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coreModels.beans.Cart;
import coreModels.beans.Order;
import coreModels.beans.ProductBean;
import coreModels.model.ProductModel;
import coreServlets.CartServlet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
public class TC_CartServlet extends Mockito{
    static private CartServlet servlet;

    @BeforeAll
    public static void init () {
        servlet = new CartServlet();
    }

    @Test
    public void testCartServlet() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        ProductModel prodottoDao= Mockito.mock(ProductModel.class);

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        Cart c= new Cart();
        ProductBean prodotto= new ProductBean();
        prodotto.setCode(3);
        c.addOrder(new Order(prodotto,4));

        when(request.getSession().getAttribute("cart")).thenReturn(c);
        List<ProductBean> list= new ArrayList<>();
        when(prodottoDao.doRetrieveList(c.getCodes(), true)).thenReturn(list);

        RequestDispatcher rd = mock(RequestDispatcher.class);
        when(request.getServletContext().getRequestDispatcher("/contentJSP/cartContent.jsp")).thenReturn(rd);

        servlet.setProductModel(prodottoDao);

        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.doGet(request, response);
        verify(response).encodeURL(captor.capture());
        assertEquals("/contentJSP/cartContent.jsp",captor.getValue());

    }
}
