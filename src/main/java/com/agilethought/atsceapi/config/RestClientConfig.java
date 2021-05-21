package com.agilethought.atsceapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import lombok.var;

@Configuration
public class RestClientConfig {
	
	@Bean
	public RestTemplate restTemplate() {
	    var factory = new SimpleClientHttpRequestFactory();
	    factory.setConnectTimeout(3000);
	    factory.setReadTimeout(3000);
	    return new RestTemplate(factory);
	}
}