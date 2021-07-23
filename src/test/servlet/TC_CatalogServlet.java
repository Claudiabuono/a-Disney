package test.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coreModels.beans.ProductBean;
import coreModels.model.Paginator;
import coreModels.model.ProductModel;
import coreServlets.CatalogServlet;
import coreServlets.ConsumerCatalog;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class TC_CatalogServlet  extends Mockito{
    static private CatalogServlet servlet;
    @BeforeAll
    public static void init () {
        servlet = new ConsumerCatalog();
    }
    @Test
    public void testCatalogServlet1() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        ProductModel prodottoDao= Mockito.mock(ProductModel.class);

        when(request.getParameter("ctgy")).thenReturn("0");
        when(request.getParameter("srch")).thenReturn("");
        when(request.getParameter("pg")).thenReturn("");


        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        List<ProductBean> lista=new ArrayList<>();
        when(prodottoDao.doRetrieveByCategory(0)).thenReturn(lista);
        servlet.setProductModel(prodottoDao);

        RequestDispatcher rd = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher(eq("Catalogo.jsp"))).thenReturn(rd);

        servlet.doGet(request, response);

        String result = stringWriter.getBuffer().toString().trim();
        assertNotNull(result);
    }
    @Test
    public void testCatalogServlet2() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        ProductModel prodottoDao= Mockito.mock(ProductModel.class);

        when(request.getParameter("ctgy")).thenReturn("");
        when(request.getParameter("srch")).thenReturn("Alice");
        when(request.getParameter("pg")).thenReturn("");


        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        List<ProductBean> lista=new ArrayList<>();
        when(prodottoDao.doRetrieveBySearch("Alice",true)).thenReturn(lista);
        servlet.setProductModel(prodottoDao);

        RequestDispatcher rd = mock(RequestDispatcher.class);
        when(response.encodeURL("Catalogo.jsp")).thenReturn("Catalogo.jsp");
        when(request.getRequestDispatcher(eq("Catalogo.jsp"))).thenReturn(rd);

        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.doGet(request, response);

        verify(rd).forward(request, response);
        verify(response).encodeURL(captor.capture());
        assertEquals("Catalogo.jsp",captor.getValue());
    }

    @Test
    public void testCatalogServlet3() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        ProductModel prodottoDao= Mockito.mock(ProductModel.class);


        when(request.getParameter("ctgy")).thenReturn("");
        when(request.getParameter("srch")).thenReturn("");
        when(request.getParameter("pg")).thenReturn("1");


        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        List<ProductBean> lista=new ArrayList<>();
        when(prodottoDao.doRetrieveAll(true)).thenReturn(lista);
        servlet.setProductModel(prodottoDao);

        RequestDispatcher rd = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher(eq("Catalogo.jsp"))).thenReturn(rd);

        servlet.doGet(request, response);


        String result = stringWriter.getBuffer().toString().trim();
        assertNotNull(result);
    }

}
