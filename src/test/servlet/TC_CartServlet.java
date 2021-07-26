package test.servlet;

import coreModels.beans.Cart;
import coreModels.beans.Order;
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
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TC_CartServlet{

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    ProductModel prodottoDao;

    @Mock
    RequestDispatcher rd;

    @Mock
    ServletContext servletContext;

    @Mock
    ServletConfig config;

    @InjectMocks
    coreServlets.CartServlet servlet;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCartServlet() throws Exception {
        when(request.getSession()).thenReturn(session);

        Cart c= new Cart();
        ProductBean prodotto= new ProductBean();
        prodotto.setCode(3);
        c.addOrder(new Order(prodotto,4));

        when(session.getAttribute("cart")).thenReturn(c);
        List<ProductBean> list= new ArrayList<>();
        when(prodottoDao.doRetrieveList(c.getCodes(), true)).thenReturn(list);


        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.init(config);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher("/contentJSP/cartContent.jsp")).thenReturn(rd);
        servlet.doGet(request, response);
        verify(response).encodeURL(captor.capture());
        assertEquals("/contentJSP/cartContent.jsp",captor.getValue());

    }
}
