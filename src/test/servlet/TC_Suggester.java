package test.servlet;

import coreModels.beans.ProductBean;
import coreModels.model.Paginator;
import coreModels.model.Pair;
import coreModels.model.ProductModel;
import coreModels.model.RegisteredModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TC_Suggester  {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    List<ProductBean> list;

    @Mock
    ProductModel model;

    @InjectMocks
    coreServlets.Suggester servlet;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test //TCS
    public void testSuggester() throws Exception {
        when(request.getParameter("srch")).thenReturn("rosalia@libero.com");
        when(model.doRetrieveBySearch("rosalia@libero.com", true)).thenReturn(list);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.service(request, response);
        String result = stringWriter.getBuffer().toString().trim();
        assertEquals("[{\"name\":\"rosalia\",\"cognome\":\"capozzolo\",\"login\":\"rosalia@libero.it\",\"password\":\"rosalia\"}]",result);

    }
}
