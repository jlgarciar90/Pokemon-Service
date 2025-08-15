package com.bankaya.challenge.pokemon.config;

import java.util.List;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurer;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import com.bankaya.challenge.pokemon.pokemonsoapservices.interceptor.RequestLogInterceptor;

@EnableWs
@Configuration
public class WebServiceConfig implements WsConfigurer {

	private final RequestLogInterceptor interceptor;

	public WebServiceConfig(RequestLogInterceptor interceptor) {
		this.interceptor = interceptor;
	}

    @Bean
    ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
            ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<>(servlet, "/webServicesPokemon/*");
	}

	@Bean(name = "pokemon")
	DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema pokemonSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("PokemonPort");
		wsdl11Definition.setLocationUri("/webServicesPokemon/");
		wsdl11Definition.setTargetNamespace("http://challengeBankaya.com/pokemon/soap");
		wsdl11Definition.setSchema(pokemonSchema);
		return wsdl11Definition;
	}

	@Bean
	XsdSchema pokemonSchema() {
		return new SimpleXsdSchema(new ClassPathResource("wsdl/pokemon.xsd"));
	}

	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
		interceptors.add(interceptor);
	}

}
