package com.bankaya.challenge.pokemon.pokemonsoapservices.interceptor;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.OutputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.transport.context.TransportContext;
import org.springframework.ws.transport.context.TransportContextHolder;
import org.springframework.ws.transport.http.HttpServletConnection;

import com.bankaya.challenge.pokemon.pokemonsoapservices.dao.LogRepository;
import com.bankaya.challenge.pokemon.pokemonsoapservices.model.LogEntity;

import jakarta.servlet.http.HttpServletRequest;

class RequestLogInterceptorTest {

    @Mock
    private LogRepository logRepository;

    @Mock
    private MessageContext messageContext;

    @Mock
    private TransportContext transportContext;

    @Mock
    private HttpServletConnection connection;

    @Mock
    private HttpServletRequest httpServletRequest;

    @InjectMocks
    private RequestLogInterceptor interceptor;
    
    @Mock
    private WebServiceMessage requestMessage,responseMessage;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        // Mock static TransportContextHolder
        TransportContextHolder.setTransportContext(transportContext);

        when(transportContext.getConnection()).thenReturn(connection);
        when(connection.getHttpServletRequest()).thenReturn(httpServletRequest);
        when(httpServletRequest.getRemoteAddr()).thenReturn("127.0.0.1");

        when(messageContext.getProperty("startTime")).thenReturn(System.currentTimeMillis());
       
        when(messageContext.getRequest()).thenReturn(requestMessage);
        when(messageContext.getResponse()).thenReturn(responseMessage);
        
        // Mock request/response que escriben contenido
        try {
            doAnswer(inv -> {
                OutputStream os = inv.getArgument(0);
                os.write("<pok:name>pikachu</pok:name>".getBytes());
                return null;
            }).when(requestMessage).writeTo(any(OutputStream.class));

            doAnswer(inv -> {
                OutputStream os = inv.getArgument(0);
                os.write("<pok:name>ok</pok:name>".getBytes());
                return null;
            }).when(responseMessage).writeTo(any(OutputStream.class));
        } catch (IOException e) {
            fail("Should not throw");
        }
    }

    @Test
    void testHandleRequestSetsStartTime() throws Exception {
        boolean result = interceptor.handleRequest(messageContext, new Object());
        assertTrue(result);
        verify(messageContext).setProperty(eq("startTime"), anyLong());
    }

    @Test
    void testHandleResponseSavesLog() throws Exception {
        boolean result = interceptor.handleResponse(messageContext, this);
        assertTrue(result);
        verify(logRepository, times(1)).save(any(LogEntity.class));
    }

    @Test
    void testHandleFaultSavesLog() throws Exception {
        boolean result = interceptor.handleFault(messageContext, this);
        assertTrue(result);
        verify(logRepository, times(1)).save(any(LogEntity.class));
    }

    @Test
    void testSaveLogHandlesIOException() throws Exception {
        // Forzar IOException en request.writeTo
    	doThrow(new IOException("boom")).when(requestMessage).writeTo(any(OutputStream.class));

        boolean result = interceptor.handleResponse(messageContext, this);
        assertTrue(result);

        verify(logRepository, times(1)).save(any(LogEntity.class)); // igual guarda log
    }
}
