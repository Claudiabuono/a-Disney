package test.servlet;

import coreModels.beans.ProductBean;
import coreModels.model.ProductModel;
import coreServlets.IndexServlet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.setProductModel(productDao);

        assertThrows(Exception.class, ()->servlet.doPost(request, response));
    }
}
