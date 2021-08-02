package test.servlet;

import coreModels.beans.Adress;
import coreModels.beans.FatturaBean;
import coreModels.beans.Registered;
import coreModels.model.FatturaModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TC_FatturaPDF {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    FatturaBean fattura;

    @Mock
    Registered userBean;

    @Mock
    ServletOutputStream myOutputStream;

    @Mock
    FatturaModel fatturaDao;

    @InjectMocks
    coreServlets.FatturaPDF servlet;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFatturaPDF() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("id")).thenReturn("3");
        when(session.getAttribute("isUser")).thenReturn(true);
        when(session.getAttribute("isAdmin")).thenReturn(false);
        when(request.getParameter("search")).thenReturn("rosalia@libero.it");
        when(session.getAttribute("user")).thenReturn(userBean);
        when(response.getOutputStream()).thenReturn(myOutputStream);
        when(fatturaDao.retrieveInvoice(userBean, 3)).thenReturn(fattura);
        when(fattura.getUser()).thenReturn(userBean);
        when(userBean.getCognome()).thenReturn("capozzolo");
        when(userBean.getName()).thenReturn("rosalia");

        servlet.doGet(request, response);

    }

}
