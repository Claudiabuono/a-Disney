package test.servlet;

import coreModels.beans.Registered;
import coreModels.beans.UserBean;
import coreModels.model.RegisteredModel;
import coreModels.model.UserModel;
import coreServlets.Login;
import coreServlets.Registration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TC_Registration extends Mockito {

    static private Registration servlet;
    static Registered user;

    @BeforeAll
    public static void init () {
        user = new Registered();
        servlet = new Registration();

        user.setLogin("annafulgione@alice.it");
        user.setPassword("anna");
        user.setName("Anna");
        user.setCognome("Fulgione");
    }

    @Test //TCS6 Utente già presente
    public void testRegistration6() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        RegisteredModel userDao= Mockito.mock(RegisteredModel.class);

        when(request.getParameter("surname")).thenReturn("russo");
        when(request.getParameter("email")).thenReturn("matteo@alice.it");
        when(request.getParameter("name")).thenReturn("matteo");
        when(request.getParameter("password")).thenReturn("matteo");

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        Registered userBean = new Registered("matteo", "russo","matteo@alice.it" ,"matteo" );
        when(userDao.registration(userBean)).thenReturn(true);
        servlet.setUserModel(userDao);

        assertThrows(Exception.class, ()->servlet.doPost(request, response));
    }

    @Test //TCS7 Campi vuoti
    public void testRegistration7() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        RegisteredModel userDao= Mockito.mock(RegisteredModel.class);

        when(request.getParameter("surname")).thenReturn(" ");
        when(request.getParameter("email")).thenReturn(" ");
        when(request.getParameter("name")).thenReturn("leo");
        when(request.getParameter("password")).thenReturn("leo");

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        Registered userBean = new Registered(" leo", " "," " ,"leo" );
        when(userDao.registration(userBean)).thenReturn(true);
        servlet.setUserModel(userDao);

        assertThrows(Exception.class, ()->servlet.doPost(request, response));
    }

    @Test //TCS8 E-mail non valida
    public void testRegistration8() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        RegisteredModel userDao= Mockito.mock(RegisteredModel.class);

        when(request.getParameter("surname")).thenReturn("fulgione");
        when(request.getParameter("email")).thenReturn("cosimo");
        when(request.getParameter("name")).thenReturn("cosimo");
        when(request.getParameter("password")).thenReturn("cosimo");

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        Registered userBean= new Registered("cosimo", "fulgione","cosimo" ,"cosimo" );
        when(userDao.registration(userBean)).thenReturn(true);
        servlet.setUserModel(userDao);

        String result = stringWriter.getBuffer().toString().trim();
        assertNotNull(result);
    }


    @Test //TCS8 Password non valida
    public void testRegistration9() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        RegisteredModel userDao= Mockito.mock(RegisteredModel.class);

        when(request.getParameter("surname")).thenReturn("fulgione");
        when(request.getParameter("email")).thenReturn("cosimo@alice.it");
        when(request.getParameter("name")).thenReturn("cosimo");
        when(request.getParameter("password")).thenReturn("c");

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        Registered userBean= new Registered("cosimo", "fulgione","cosimo@alice.it" ,"c" );

        servlet.setUserModel(userDao);

        String result = stringWriter.getBuffer().toString().trim();
        assertNotNull(result);
    }
    @Test //Buon fine
    public void testRegistration10() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        RegisteredModel userDao= Mockito.mock(RegisteredModel.class);

        when(request.getParameter("surname")).thenReturn("fulgione");
        when(request.getParameter("email")).thenReturn("cosimo@alice.it");
        when(request.getParameter("name")).thenReturn("cosimo");
        when(request.getParameter("password")).thenReturn("cosimo1234");

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        Registered userBean= new Registered("cosimo", "fulgione","cosimo@alice.it" ,"cosimo1234" );
        when(userDao.registration(userBean)).thenReturn(true);
        servlet.setUserModel(userDao);
        servlet.doGet(request, response);

        String result = stringWriter.getBuffer().toString().trim();
        assertNotNull(result);
    }
}
