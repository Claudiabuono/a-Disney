package test.integration;

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
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TC_FatturaPDFIntegration {
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @InjectMocks
    coreServlets.FatturaPDF servlet;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFatturaPDF() throws Exception {
        ServletOutputStream myOutputStream= new ServletOutputStream() {
            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {

            }

            @Override
            public void write(int b) throws IOException {

            }
        };
        Registered userBean= new Registered("rosalia", "capozzolo", "rosaia@libero.it", "rosalia");

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("id")).thenReturn("1");
        when(session.getAttribute("isUser")).thenReturn(true);
        when(session.getAttribute("isAdmin")).thenReturn(false);
        when(request.getParameter("search")).thenReturn("rosalia@libero.it");
        when(session.getAttribute("user")).thenReturn(userBean);
        when(response.getOutputStream()).thenReturn(myOutputStream);

        servlet.doGet(request, response);
        ArgumentCaptor<byte[]> valueCapture =ArgumentCaptor.forClass(byte[].class);
        verify(myOutputStream).write(valueCapture.capture());
        byte[] writtenData = valueCapture.getValue();
        assertNotNull(writtenData);
    }
}
