package test.servlet;

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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TC_IndexServlet{

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    ProductModel productDao;

    @Mock
    RequestDispatcher rd;

    @Mock
    List<ProductBean> list;

    @InjectMocks
    coreServlets.IndexServlet servlet;



    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test //TCS
    public void testIndexServlet() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(productDao.doRetrieveByDiscount(30, true)).thenReturn(list);

        assertThrows(Exception.class, ()->servlet.doPost(request, response));
    }
    @Test //TCS
    public void testIndexServlet2() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(productDao.doRetrieveByDiscount(30, true)).thenReturn(list);
        when(request.getRequestDispatcher("/contentJSP/ProductCard.jsp")).thenReturn(rd);

        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.doPost(request, response);
        verify(request).getRequestDispatcher(captor.capture());
        assertEquals("/contentJSP/ProductCard.jsp",captor.getValue());
    }
}
