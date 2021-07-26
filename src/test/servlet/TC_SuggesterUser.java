package test.servlet;

import coreModels.beans.Registered;
import coreModels.model.RegisteredModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TC_SuggesterUser {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    RegisteredModel registeredDao;

    @InjectMocks
    coreServlets.SuggesterUser servlet;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test //TCS
    public void testSuggesterUser() throws Exception {
        when(request.getParameter("srch")).thenReturn("rosalia@libero.it");
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        List<Registered> list= new ArrayList<>();
        Registered e = new Registered("rosalia", "capozzolo", "rosalia@libero.it", "rosalia");
        list.add(e);
        when(registeredDao.doRetrieveBySearch("rosalia@libero.it")).thenReturn((ArrayList<Registered>) list);
        servlet.service(request, response);
        String result = stringWriter.getBuffer().toString().trim();
        assertEquals("[{\"name\":\"rosalia\",\"cognome\":\"capozzolo\",\"login\":\"rosalia@libero.it\",\"password\":\"rosalia\"}]",result);
    }
}
