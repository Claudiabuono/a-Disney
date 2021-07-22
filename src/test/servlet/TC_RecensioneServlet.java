package test.servlet;

import coreModels.beans.ProductBean;
import coreModels.beans.RecensioneBean;
import coreModels.beans.Registered;
import coreModels.beans.UserBean;
import coreModels.model.RecensioneModel;
import coreModels.model.UserModel;
import coreServlets.RecensioneServlet;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TC_RecensioneServlet extends Mockito {

    static RecensioneBean recensione;
    static RecensioneServlet servlet;
/*
    @Test //TCS
    public void testRecensioneView() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        RecensioneModel recensioneDao= Mockito.mock(RecensioneModel.class);

        when(request.getParameter("act")).thenReturn("view");
        when(request.getParameter("product")).thenReturn(" ");


        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        Registered user=new Registered();
        ProductBean bean= new ProductBean();
        List<RecensioneBean> lista= new ArrayList<>();
        when(RecensioneModel.userComment(user, bean)).thenReturn((RecensioneBean) lista);
        when(RecensioneModel.getComments(bean)).thenReturn((List<RecensioneBean>) lista);
        when(RecensioneModel.mediumVote(bean)).thenReturn((List<RecensioneBean>) lista);


        servlet.setRecensioneModel(recensioneDao);

        assertThrows(Exception.class, ()->servlet.doPost(request, response));
    }
/*
    @Test //TCS Campo voto vuoto
    public void testRecensione2() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        UserModel userDao= Mockito.mock(UserModel.class);

        when(request.getParameter("act")).thenReturn("view");
        when(request.getParameter("product")).thenReturn(" ");
        when(request.getParameter("vote")).thenReturn(" ");
        when(request.getParameter("comment")).thenReturn("Prodotto curato nei minimi dettagli");

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        RecensioneBean recensioneBean = new RecensioneBean("Prodotto curato nei minimi dettagli", 0, "rosalia");
        when(userDao.login("","rosa")).thenReturn(userBean);
        servlet.setRecensioneModel(recensioneDao);

        assertThrows(Exception.class, ()->servlet.doPost(request, response));
    }

 */
}
