package test.servlet;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coreModels.beans.Registered;
import coreModels.beans.UserBean;
import coreModels.model.UserModel;
import coreServlets.AdminCatalog;
import coreServlets.Login;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
public class TC_AdminCatalog extends Mockito{

    static private AdminCatalog servlet;
    @BeforeAll
    public static void init () {

        servlet = new AdminCatalog();
    }

    @Test
    public void testAdminCatalog() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
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
