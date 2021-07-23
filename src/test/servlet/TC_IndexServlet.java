package test.servlet;

import coreModels.beans.ProductBean;
import coreModels.model.ProductModel;
import coreServlets.IndexServlet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TC_IndexServlet extends Mockito{

    private static final long serialVersionUID = 1L;
    static boolean isDataSource = false;

    static ProductBean product;
    static private IndexServlet servlet;

    @BeforeAll
    public static void init () {
        servlet = new IndexServlet();
    }

    @Test //TCS
    public void testIndexServlet() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        ProductModel productDao= Mockito.mock(ProductModel.class);

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        List<ProductBean> list= new ArrayList<>();
        when(productDao.doRetrieveByDiscount(30, true)).thenReturn(list);
        servlet.setProductModel(productDao);

        assertThrows(Exception.class, ()->servlet.doPost(request, response));
    }
    @Test //TCS
    public void testIndexServlet2() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        ProductModel productDao= Mockito.mock(ProductModel.class);

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        List<ProductBean> list= new ArrayList<>();
        when(productDao.doRetrieveByDiscount(30, true)).thenReturn(list);
        RequestDispatcher rd = mock(RequestDispatcher.class);
        when(request.getServletContext().getRequestDispatcher(eq("/contentJSP/ProductCard.jsp"))).thenReturn(rd);

        servlet.setProductModel(productDao);

        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.doPost(request, response);
        verify(response).encodeURL(captor.capture());
        assertEquals("/contentJSP/ProductCard.jsp",captor.getValue());
    }
}
