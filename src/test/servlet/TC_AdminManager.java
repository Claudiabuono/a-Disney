package test.servlet;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coreModels.model.FatturaModel;
import coreServlets.AdminManager;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
public class TC_AdminManager extends Mockito {
    static private AdminManager servlet;
    @BeforeAll
    public static void init () {
       servlet = new AdminManager();
    }
    @Test
    public void testAdminManager() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        FatturaModel fatturaDao= Mockito.mock(FatturaModel.class);

        when(request.getParameter("search")).thenReturn("rosalia@libero.it");
        when(request.getParameter("da")).thenReturn("2020,8,1");
        when(request.getParameter("a")).thenReturn("2020,8,4");
        when(request.getParameter("pg")).thenReturn("1");

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        servlet.setFatturaModel(fatturaDao);

        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.doGet(request, response);
        verify(response).encodeURL(captor.capture());
        assertEquals("/Ordini.jsp",captor.getValue());
    }
}
