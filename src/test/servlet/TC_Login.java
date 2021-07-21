package test.servlet;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coreModels.beans.Registered;
import coreModels.beans.UserBean;
import coreModels.model.UserModel;
import coreServlets.Login;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class TC_Login extends Mockito {

    static private Login servlet;
    static UserBean user;

    @BeforeAll
    public static void init () {
        user = new Registered();
        servlet = new Login();

        user.setLogin("rosalia@libero.it");
        user.setPassword("rosalia");
    }
    @Test //TCS1
    public void testLogin1() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        UserModel userDao= Mockito.mock(UserModel.class);

        when(request.getParameter("username")).thenReturn("rosa@libero.it");
        when(request.getParameter("password")).thenReturn("rosa1234");

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        UserBean userBean = new Registered("rosa", "capo","rosa@libero.it" ,"rosa1234" );
        when(userDao.login("rosa@libero.it","rosa")).thenReturn(userBean);
        servlet.setUserModel(userDao);

        assertThrows(Exception.class, ()->servlet.doPost(request, response));
    }
    @Test //TCS2
    public void testLogin2() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        UserModel userDao= Mockito.mock(UserModel.class);

        when(request.getParameter("username")).thenReturn("");
        when(request.getParameter("password")).thenReturn("rosa1234");

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        UserBean userBean = new Registered("rosa", "capo","rosa@libero.it" ,"rosa1234" );
        when(userDao.login("","rosa")).thenReturn(userBean);
        servlet.setUserModel(userDao);

        assertThrows(Exception.class, ()->servlet.doPost(request, response));
    }
    @Test //TCS3
    public void testLogin3() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        UserModel userDao= Mockito.mock(UserModel.class);

        when(request.getParameter("username")).thenReturn("rosa@libero.it");
        when(request.getParameter("password")).thenReturn("");

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        UserBean userBean = new Registered("rosa", "capo","rosa@libero.it" ,"rosa1234" );
        when(userDao.login("rosa@libero.it","")).thenReturn(userBean);
        servlet.setUserModel(userDao);

        String result = stringWriter.getBuffer().toString().trim();
        assertNotNull(result);
    }

    @Test //TCS4
    public void testLogin4() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        UserModel userDao= Mockito.mock(UserModel.class);

        when(request.getParameter("username")).thenReturn("rosalia");
        when(request.getParameter("password")).thenReturn("rosalia");

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        UserBean userBean = new Registered("rosa", "capo","rosa@libero.it" ,"rosa1234" );
        when(userDao.login("rosalia","rosalia")).thenReturn(userBean);
        servlet.setUserModel(userDao);

        String result = stringWriter.getBuffer().toString().trim();
        assertNotNull(result);
    }

    @Test //TCS5
    public void testLogin5() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        UserModel userDao= Mockito.mock(UserModel.class);

        when(request.getParameter("username")).thenReturn("rosalia@libero.it");
        when(request.getParameter("password")).thenReturn("rosalia");

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        UserBean userBean = new Registered("rosalia", "capozzolo","rosalia@libero.it" ,"rosalia" );
        when(userDao.login("rosalia@libero.it","rosalia")).thenReturn(userBean);
        servlet.setUserModel(userDao);
        servlet.doPost(request, response);

        String result = stringWriter.getBuffer().toString().trim();
        assertNotNull(result);
    }

}
