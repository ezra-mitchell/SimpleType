package com.afs.ezra.simpletype.webapp.themes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ThemeManager {
	
	private final RestTemplate restTemplate;

	@Value("${manager.url}")
	private String url;
	
	public ResponseEntity<ThemeDto> getTheme(String themeName) {
		HttpEntity<String> entity = new HttpEntity<String>("");
		return restTemplate.exchange(url + "/themes/" + themeName, HttpMethod.GET,entity,  ThemeDto.class);
	}
	
	public ResponseEntity<String[]> getAllThemes(){
		HttpEntity<String> entity = new HttpEntity<String>("");
		return restTemplate.exchange(url + "/themes/list", HttpMethod.GET, entity, String[].class);
	}
	
	public ResponseEntity<Void> saveTheme(ThemeDto theme) {
		HttpEntity<ThemeDto> entity = new HttpEntity<ThemeDto>(theme);
		return restTemplate.exchange(url + "/themes", HttpMethod.POST, entity, Void.class);
	}
	
	public ResponseEntity<Void> updateTheme(ThemeDto theme) {
		HttpEntity<ThemeDto> entity = new HttpEntity<ThemeDto>(theme);
		return restTemplate.exchange(url + "/themes", HttpMethod.PUT, entity, Void.class);
	}
	
	public ResponseEntity<Void> deleteTheme(String themeName) {
		HttpEntity<String> entity = new HttpEntity<String>("");
		return restTemplate.exchange(url + "/themes/" + themeName, HttpMethod.PUT, entity, Void.class);
	}

}
