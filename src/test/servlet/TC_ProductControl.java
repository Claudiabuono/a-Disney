package test.servlet;

import coreModels.beans.Cart;
import coreModels.beans.ProductBean;
import coreModels.beans.RecensioneBean;
import coreModels.model.ProductModel;
import coreModels.model.UserModel;
import coreServlets.ProductControl;
import coreServlets.SuggesterUser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TC_ProductControl extends Mockito {
    private static final long serialVersionUID = 1L;

    static ProductControl servlet;

    @BeforeAll
    public static void init () {
        servlet = new ProductControl();
    }

    @Test //TCS
    public void testAddC() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        ProductModel productDao= Mockito.mock(ProductModel.class);
        Cart c= new Cart();
        when((Cart)request.getSession().getAttribute("cart")).thenReturn(c);
        when(request.getParameter("act")).thenReturn("addC");
        when(request.getParameter("id")).thenReturn("3");
        when(request.getParameter("qty")).thenReturn("2");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        ProductBean b= new ProductBean();

        when(productDao.doRetrieveByKey(3, true)).thenReturn(b);

        servlet.setProductModel(productDao);
        servlet.doGet(request, response);

        String result = stringWriter.getBuffer().toString().trim();
        assertEquals("[{\"name\":\"rosalia\",\"cognome\":\"capozzolo\",\"login\":\"rosalia@libero.it\",\"password\":\"rosalia\"}]",result);

    }

    @Test //TCS
    public void testView() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        ProductModel productDao= Mockito.mock(ProductModel.class);
        Cart c= new Cart();
        when((Cart)request.getSession().getAttribute("cart")).thenReturn(c);
        when(request.getParameter("act")).thenReturn("view");


        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        ProductBean b= new ProductBean();

        when(productDao.doRetrieveByKey(3, true)).thenReturn(b);

        servlet.setProductModel(productDao);
        servlet.doGet(request, response);

        String result = stringWriter.getBuffer().toString().trim();
        assertEquals("[{\"name\":\"rosalia\",\"cognome\":\"capozzolo\",\"login\":\"rosalia@libero.it\",\"password\":\"rosalia\"}]",result);

    }
}
