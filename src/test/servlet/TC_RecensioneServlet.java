package test.servlet;

import coreModels.beans.ProductBean;
import coreModels.beans.RecensioneBean;
import coreModels.beans.Registered;
import coreModels.beans.UserBean;
import coreModels.model.DM.FatturaModelDM;
import coreModels.model.FatturaModel;
import coreModels.model.RecensioneModel;
import coreModels.model.UserModel;
import coreServlets.RecensioneServlet;
import coreServlets.Suggester;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TC_RecensioneServlet extends Mockito {

    static RecensioneModel recensione;
    static FatturaModel fattura;
    static RecensioneServlet servlet;


    @BeforeAll
    public static void init () {
        servlet = new RecensioneServlet();
    }
    @Test //TCS
    public void testRecensioneView() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        RecensioneModel recensioneDao= Mockito.mock(RecensioneModel.class);
        FatturaModel fatturaDao= Mockito.mock(FatturaModel.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        when(request.getParameter("act")).thenReturn("view");
        ProductBean b= new ProductBean ("prova", " prova", "prova ", "prova", "prova", 0, 6, 10, 20,8);
        b.setPrice(45);
        when(request.getAttribute("product")).thenReturn(b);
        Registered r= new Registered();
        when(request.getSession().getAttribute("user")).thenReturn(r);

        List<RecensioneBean> list= new ArrayList<>();
        RecensioneBean recensioneBean= new RecensioneBean();
        list.add(recensioneBean);
        when(recensioneDao.getComments(b)).thenReturn(list);
        when(recensioneDao.userComment(r, b)).thenReturn(recensioneBean);
        when(fatturaDao.hasPurchased(b, r)).thenReturn(true);

        servlet.setRecensioneModel(recensioneDao);
        servlet.doGet(request,response);

        boolean l= (boolean) request.getAttribute("payed");
        assertEquals(true,l);

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
