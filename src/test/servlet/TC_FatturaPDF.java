package test.servlet;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coreModels.beans.FatturaBean;
import coreModels.beans.Registered;
import coreModels.model.FatturaModel;
import coreServlets.FatturaPDF;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
public class TC_FatturaPDF extends Mockito{
    static private FatturaPDF servlet;
    @BeforeAll
    public static void init () {
        servlet = new FatturaPDF();
    }

    @Test
    public void testFatturaPDF() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        FatturaModel fatturaDao= Mockito.mock( FatturaModel.class);

        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("search")).thenReturn("rosalia@libero.it");

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        Registered userBean = new Registered();
        FatturaBean fattura= new FatturaBean();
        when(fatturaDao.retrieveInvoice(userBean, 1)).thenReturn(fattura);
        servlet.setFatturaModel(fatturaDao);
        servlet.doGet(request, response);

        String result = stringWriter.getBuffer().toString().trim();
        assertNotNull(result);
    }

}
