package test.servlet;

import coreModels.beans.RecensioneBean;
import coreModels.model.ProductModel;
import coreModels.model.UserModel;
import coreServlets.ProductControl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TC_ProductControl extends Mockito {
    private static final long serialVersionUID = 1L;

    static ProductControl servlet;

  /*  @Test //TCS Campo voto vuoto
    public void testAddC() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        ProductModel productDao= Mockito.mock(ProductModel.class);

        when(request.getParameter("act")).thenReturn("addC");

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        RecensioneBean recensioneBean = new RecensioneBean("Prodotto curato nei minimi dettagli", 0, "rosalia");
        when(userDao.login("","rosa")).thenReturn(userBean);
        servlet.setRecensioneModel(recensioneDao);

        assertThrows(Exception.class, ()->servlet.doPost(request, response));
    }*/


}
