package test.servlet;

import coreModels.beans.FatturaBean;
import coreModels.beans.ProductBean;
import coreModels.beans.Registered;
import coreModels.model.FatturaModel;
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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("search")).thenReturn("rosalia@libero.it");
        when(request.getSession()).thenReturn(session);

       when(response.getOutputStream()).thenReturn(myOutputStream);

        Registered userBean = new Registered();
        FatturaBean f= new FatturaBean();
        f.setCod(2);
        f.setDate(new GregorianCalendar(2020,5,8));
        when(fatturaDao.retrieveInvoice(userBean, 1)).thenReturn(f);

        servlet.doGet(request, response);
        ArgumentCaptor<byte[]> valueCapture = null;
        verify(myOutputStream).write(valueCapture.capture());
        byte[] writtenData = valueCapture.getValue();
        assertNotNull(writtenData);
    }

}
