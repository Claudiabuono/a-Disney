package test.servlet;

import coreModels.beans.ProductBean;
import coreModels.model.ProductModel;
import coreServlets.Suggester;
import coreServlets.SuggesterUser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TC_SuggesterUser extends Mockito {
    private static final long serialVersionUID = 1L;
    static boolean isDataSource = false;

    static ProductBean product;
    static private SuggesterUser servlet;


    @BeforeAll
    public static void init () {
        servlet = new SuggesterUser();
    }

    @Test //TCS
    public void testSuggesterUser() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        ProductModel productDao= Mockito.mock(ProductModel.class);

        when(request.getParameter("srch")).thenReturn("");

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        List<ProductBean> list= new ArrayList<>();
        when(productDao.doRetrieveBySearch("Aurora", true)).thenReturn(list);
        servlet.setProductModel(productDao);

        String result = stringWriter.getBuffer().toString().trim();
        assertNotNull(result);
    }
}
