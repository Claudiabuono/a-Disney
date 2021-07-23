package test.servlet;

import coreModels.beans.ProductBean;
import coreModels.beans.Registered;
import coreModels.model.ProductModel;
import coreModels.model.RegisteredModel;
import coreServlets.Suggester;
import coreServlets.SuggesterUser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TC_SuggesterUser extends Mockito {
    private static final long serialVersionUID = 1L;
    static boolean isDataSource = false;

    static ProductBean product;
    static private SuggesterUser servlet;


    @BeforeAll
    public static void init () {
        servlet = new SuggesterUser();
    }

    @Test //TCS
    public void testSuggesterUser() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        RegisteredModel dao= Mockito.mock(RegisteredModel.class);
        when(request.getParameter("srch")).thenReturn("rosalia@libero.it");

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        List<Registered> list= new ArrayList<>();
        Registered e = new Registered("rosalia", "capozzolo", "rosalia@libero.it", "rosalia");
        list.add(e);
        when(dao.doRetrieveBySearch("rosalia@libero.it")).thenReturn((ArrayList<Registered>) list);
        servlet.setProductModel(dao);

        servlet.service(request, response);
        String result = stringWriter.getBuffer().toString().trim();
        assertEquals("[{\"name\":\"rosalia\",\"cognome\":\"capozzolo\",\"login\":\"rosalia@libero.it\",\"password\":\"rosalia\"}]",result);
    }
}
