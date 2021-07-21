package test.integration;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coreModels.beans.Registered;
import coreModels.beans.UserBean;
import coreServlets.Login;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
public class TC_LoginIntegration extends Mockito{
    static private Login servlet;
    static UserBean user;

    @BeforeAll
    public static void init () {
        user = new Registered();
        servlet = new Login();

        user.setLogin("rosalia@libero.it");
        user.setPassword("rosalia");
    }

    @Test
    public void testLogin() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        when(request.getParameter("username")).thenReturn("rosalia@libero.it");
        when(request.getParameter("password")).thenReturn("rosalia");

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);

        String result = stringWriter.getBuffer().toString().trim();
        assertNotNull(result);
    }
}
