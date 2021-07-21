package test.servlet;

import coreModels.model.ProductModel;
import coreServlets.IndexServlet;
import coreServlets.Logout;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TC_Logout extends Mockito {
    private static final long serialVersionUID = 1L;

    static private Logout servlet;
    @BeforeAll
    public static void init () {
        servlet = new Logout();
    }

    @Test //TCS
    public void testLogout() throws Exception {
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
