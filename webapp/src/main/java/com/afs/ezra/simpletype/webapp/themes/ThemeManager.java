package com.afs.ezra.simpletype.webapp.themes;

import org.springframework.http.HttpStatus;

public interface ThemeManager {

	public ThemeView getTheme(String themeName);
	
	public String[] getAllThemes();
	
	public HttpStatus saveTheme(ThemeView theme);
	
	public HttpStatus updateTheme(ThemeView theme);
	
	public HttpStatus deleteTheme(String themeName);
}
