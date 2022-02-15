package com.afs.ezra.simpletype.webapp.themes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ThemeManager {
	
	private final RestTemplate restTemplate;

	@Value("${manager.url}")
	private String url;
	
	public ThemeView getTheme(String themeName) {
		HttpEntity<String> entity = new HttpEntity<String>("");
		return restTemplate.exchange(url + "/themes/" + themeName, HttpMethod.GET,entity, ThemeView.class).getBody();
	}
	
	public String[] getAllThemes(){
		HttpEntity<String> entity = new HttpEntity<String>("");
		return restTemplate.exchange(url + "/themes/list", HttpMethod.GET, entity, String[].class).getBody();
	}
	
	public HttpStatus saveTheme(ThemeView theme) {
		HttpEntity<ThemeView> entity = new HttpEntity<ThemeView>(theme);
		return restTemplate.exchange(url + "/themes", HttpMethod.POST, entity, Void.class).getStatusCode();
	}
	
	public HttpStatus updateTheme(ThemeView theme) {
		HttpEntity<ThemeView> entity = new HttpEntity<ThemeView>(theme);
		return restTemplate.exchange(url + "/themes", HttpMethod.PUT, entity, Void.class).getStatusCode();
	}
	
	public HttpStatus deleteTheme(String themeName) {
		HttpEntity<String> entity = new HttpEntity<String>("");
		return restTemplate.exchange(url + "/themes/" + themeName, HttpMethod.DELETE, entity, Void.class).getStatusCode();
	}

}
