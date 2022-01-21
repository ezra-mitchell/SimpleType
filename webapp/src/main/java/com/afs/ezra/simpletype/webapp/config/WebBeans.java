package com.afs.ezra.simpletype.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebBeans {

	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
