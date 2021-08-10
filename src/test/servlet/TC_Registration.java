package test.servlet;

import coreModels.beans.Registered;
import coreModels.beans.UserBean;
import coreModels.model.RegisteredModel;
import coreModels.model.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class TC_Registration  {
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    UserBean user;

    @Mock
    UserModel userDao;

    @Mock
    Registered registredDao;

    @Mock
    RequestDispatcher rd;

    @InjectMocks
    coreServlets.Registration servlet;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegistration6() throws Exception {
        when(request.getParameter("surname")).thenReturn("Lamberti");
        when(request.getParameter("email")).thenReturn("francescaLamberti@alice.it");
        when(request.getParameter("name")).thenReturn("francesca");
        when(request.getParameter("password")).thenReturn("francescaLamberti");
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        servlet.doPost(request, response);
        verify(response).sendRedirect(captor.capture());
        assertEquals("index.jsp",captor.getValue());

        RegisteredModel registred= new RegisteredModel();
        registred.deleteRegistrato("francescaLamberti@alice.it");

    }
}
