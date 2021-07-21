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

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.setFatturaModel(fatturaDao);
        servlet.doGet(request, response);

        String result = stringWriter.getBuffer().toString().trim();
        assertNotNull(result);
    }
}
