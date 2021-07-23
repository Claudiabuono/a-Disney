package test.servlet;

import coreModels.beans.ProductBean;
import coreModels.model.ProductModel;
import coreServlets.IndexServlet;
import coreServlets.Suggester;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TC_Suggester extends Mockito {

    private static final long serialVersionUID = 1L;
    static boolean isDataSource = false;

    static ProductBean product;
    static private Suggester servlet;


    @BeforeAll
    public static void init () {
        servlet = new Suggester();
    }

    @Test //TCS
    public void testSuggester() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        ProductModel productDao= Mockito.mock(ProductModel.class);
        when(request.getParameter("srch")).thenReturn("peluche");

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        List<ProductBean> list= new ArrayList<>();
        ProductBean b= new ProductBean ("prova", " prova", "prova ", "prova", "prova", 0, 6, 10, 20,8);
        b.setPrice(45);
        list.add(b);
        servlet.setProductModel(productDao);
        when(productDao.doRetrieveBySearch("peluche", true)).thenReturn(list);

        servlet.service(request, response);
        String result = stringWriter.getBuffer().toString().trim();
        assertEquals("[{\"id\":0,\"name\":\"prova\",\"img\":\"prova\",\"isinDicount\":true,\"price\":\"54.00\",\"priceDisc\":\"49.68\",\"discount\":8.0,\"qty\":6,\"iva\":20.0,\"character\":\"prova \",\"category\":\"Articoli per la casa\"}]",result);
    }
}
