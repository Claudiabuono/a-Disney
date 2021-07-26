package test.servlet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coreModels.beans.ProductBean;
import coreModels.model.Paginator;
import coreModels.model.Pair;
import coreModels.model.ProductModel;
import coreServlets.ConsumerCatalog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TC_ConsumerCatalog {
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    Paginator<ProductBean> p;

    @Mock
    Pair<ProductBean> pair;

    @Mock
    RequestDispatcher rd;

    @Mock
    ProductModel prodottoDao;

    @Mock
    ServletConfig sc;

    @InjectMocks
    coreServlets.ConsumerCatalog servlet;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testConsumerCatalog1() throws Exception {
        when(request.getParameter("ctgy")).thenReturn("0");
        when(request.getParameter("srch")).thenReturn("");
        when(request.getParameter("pg")).thenReturn("1");

        List<ProductBean> lista=new ArrayList<>();
        ProductBean b= new ProductBean ("prova", "prova", "prova", "prova", "prova", 0, 6, 10, 20,8);
        lista.add(b);
        when(prodottoDao.doRetrieveByCategory(0)).thenReturn(lista);
        when(p.paginate(lista)).thenReturn(pair);
        when(response.encodeURL("Catalogo.jsp")).thenReturn("Catalogo.jsp");
        when(request.getRequestDispatcher("Catalogo.jsp")).thenReturn(rd);

        servlet.init(sc);
        servlet.doGet(request, response);
        verify(rd).forward(request, response);
        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        verify(response).encodeURL(captor.capture());
        assertEquals("Catalogo.jsp",captor.getValue());
    }

    @Test
    public void testConsumerCatalog2() throws Exception {
        when(request.getParameter("ctgy")).thenReturn("");
        when(request.getParameter("srch")).thenReturn("Alice");
        when(request.getParameter("pg")).thenReturn("1");

        List<ProductBean> lista=new ArrayList<>();
        ProductBean b= new ProductBean ("prova", "prova", "Alice", "prova", "prova", 0, 6, 10, 20,8);
        lista.add(b);

        when(prodottoDao.doRetrieveBySearch("Alice",true)).thenReturn(lista);
        when(p.paginate(lista)).thenReturn(pair);
        when(response.encodeURL("Catalogo.jsp")).thenReturn("Catalogo.jsp");

        when(request.getRequestDispatcher("Catalogo.jsp")).thenReturn(rd);
        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.init(sc);
        servlet.doGet(request, response);

        verify(rd).forward(request, response);
        verify(response).encodeURL(captor.capture());
        assertEquals("Catalogo.jsp",captor.getValue());
    }

    @Test
    public void testConsumerCatalog3() throws Exception {
        when(request.getParameter("ctgy")).thenReturn("");
        when(request.getParameter("srch")).thenReturn("");
        when(request.getParameter("pg")).thenReturn("1");

        List<ProductBean> lista=new ArrayList<>();
        ProductBean b= new ProductBean ("prova", "prova", "Alice", "prova", "prova", 0, 6, 10, 20,8);
        lista.add(b);

        when(prodottoDao.doRetrieveAll(true)).thenReturn(lista);
        when(p.paginate(lista)).thenReturn(pair);
        when(response.encodeURL("Catalogo.jsp")).thenReturn("Catalogo.jsp");

        when(request.getRequestDispatcher("Catalogo.jsp")).thenReturn(rd);
        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.init(sc);
        servlet.doGet(request, response);

        verify(rd).forward(request, response);
        verify(response).encodeURL(captor.capture());
        assertEquals("Catalogo.jsp",captor.getValue());
    }
}
