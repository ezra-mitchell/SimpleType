package com.afs.ezra.simpletype.webapp.themes;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ThemeManager {
	
	
	public ResponseEntity<ThemeDto> getTheme(String themeName) {
		return null;
	}
	
	public List<ThemeDto> getAllThemes(){
		return null;
	}
	
	public void saveTheme(ThemeDto theme) {
	}
	
	public void updateTheme(ThemeDto theme) {
	}
	
	public void deleteTheme(String themeName) {
	}

}
