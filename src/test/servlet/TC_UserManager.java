package test.servlet;

import coreModels.beans.FatturaBean;
import coreModels.beans.Registered;
import coreModels.beans.UserBean;
import coreModels.model.FatturaModel;
import coreModels.model.RegisteredModel;
import coreModels.model.UserModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TC_UserManager extends Mockito {

    static Registered registered;
    static FatturaBean fatt;
/*
    @Test //TCS5
    public void testUserManager() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        RegisteredModel userDao= Mockito.mock(RegisteredModel.class);

        when(request.getParameter("op")).thenReturn(" ");
        when(request.getParameter("user")).thenReturn(" ");

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

 */
}
