package com.bankaya.challenge.pokemon.pokemonsoapservices.interceptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.transport.context.TransportContext;
import org.springframework.ws.transport.context.TransportContextHolder;
import org.springframework.ws.transport.http.HttpServletConnection;

import com.bankaya.challenge.pokemon.pokemonsoapservices.dao.LogRepository;
import com.bankaya.challenge.pokemon.pokemonsoapservices.model.LogEntity;

@Component
public class RequestLogInterceptor implements EndpointInterceptor{
	
	private final LogRepository loggerRepository;
	
	public RequestLogInterceptor(LogRepository logger) {
		this.loggerRepository = logger;
	}

	@Override
	public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
		messageContext.setProperty("startTime", System.currentTimeMillis());
		return true;
	}

	@Override
	public boolean handleResponse(MessageContext messageContext, Object endpoint) throws Exception {
		saveLog(messageContext, endpoint, "Response");
		return true;
	}

	@Override
	public boolean handleFault(MessageContext messageContext, Object endpoint) throws Exception {
		//Se guardan respuestas fault
		saveLog(messageContext, endpoint,"Fault");
		return true;
	}
	
	@Override
	public void afterCompletion(MessageContext messageContext, Object endpoint, Exception ex) throws Exception {
		//No se modifica nada en afterCompletion
	}
	
	private void saveLog(MessageContext messageContext, Object endpoint, String tipoLog) {
        TransportContext transportContext = TransportContextHolder.getTransportContext();
        HttpServletConnection connection = (HttpServletConnection) transportContext.getConnection();

        LogEntity log = new LogEntity();
        log.setIpOrigin(connection.getHttpServletRequest().getRemoteAddr());
        log.setDateRequest(LocalDateTime.now());
        log.setMethodExecuted(endpoint.getClass().getSimpleName()+"("+ tipoLog +")");
        log.setElapsedTime(System.currentTimeMillis()- (Long) messageContext.getProperty("startTime"));

        // Obtenemos XML Request
        ByteArrayOutputStream requestOut = new ByteArrayOutputStream();
        ByteArrayOutputStream responseOut = new ByteArrayOutputStream();
        try {
        	// Obtenemos XML Request
			messageContext.getRequest().writeTo(requestOut);
			log.setRequestBody(requestOut.toString(StandardCharsets.UTF_8));
			
			// Obtenemos XML Response
	        messageContext.getResponse().writeTo(responseOut);
	        log.setResponseBody(responseOut.toString(StandardCharsets.UTF_8));
		
        } catch (IOException e) {
        	//Aqui se podria agregar logger
			e.printStackTrace();
		}
       
        loggerRepository.save(log);
    }

	

}
