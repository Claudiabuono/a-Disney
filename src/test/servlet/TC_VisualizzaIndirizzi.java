package test.servlet;

import coreModels.beans.ProductBean;
import coreModels.beans.Registered;
import coreModels.model.Paginator;
import coreModels.model.Pair;
import coreModels.model.ProductModel;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TC_VisualizzaIndirizzi {
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    Registered user;

    @Mock
    RequestDispatcher rd;

    @InjectMocks
    coreServlets.VisualizzaIndirizzi servlet;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testConsumerCatalog1() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(response.encodeURL("/protected-page.jsp")).thenReturn("/protected-page.jsp");
        when(request.getRequestDispatcher("/protected-page.jsp")).thenReturn(rd);

        servlet.doGet(request, response);
        verify(rd).forward(request, response);
        ArgumentCaptor<String> captor= ArgumentCaptor.forClass(String.class);
        verify(response).encodeURL(captor.capture());
        assertEquals("/protected-page.jsp",captor.getValue());

    }
}
