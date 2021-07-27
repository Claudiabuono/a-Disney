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
        when(request.getParameter("id")).thenReturn("1");
        when(session.getAttribute("isUser")).thenReturn(null);
        when(session.getAttribute("isAdmin")).thenReturn(true);
        when(request.getParameter("search")).thenReturn("rosalia@libero.it");
        Registered userBean = new Registered("rosalia", "capozzolo", "rosalia@libero.it", "rosalia");
        when(session.getAttribute("user")).thenReturn(userBean);
        when(response.getOutputStream()).thenReturn(myOutputStream);

        Registered r= new Registered();
        r.setLogin("rosalia@libero.it");
        FatturaBean f= new FatturaBean();
        f.setCod(2);
        f.setDate(new GregorianCalendar(2020,5,8));
        f.setShipping(new Adress("via roma", 4,84567, "Salerno", "Felitto"));
        f.setUser(userBean);
        when(fatturaDao.retrieveInvoice(r, 2)).thenReturn(f);
        when(fattura.getUser()).thenReturn(userBean);

        servlet.doGet(request, response);
      ArgumentCaptor<byte[]> valueCapture =ArgumentCaptor.forClass(byte[].class);
        verify(myOutputStream).write(valueCapture.capture());
        byte[] writtenData = valueCapture.getValue();
        assertNotNull(writtenData);
    }

}
