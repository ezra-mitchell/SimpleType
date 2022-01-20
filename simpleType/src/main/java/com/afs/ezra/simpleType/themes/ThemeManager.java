package com.afs.ezra.simpleType.themes;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ThemeManager {
	
	private final ThemeDao themeDao;
	
	public Theme getTheme(String themeName) {
		return themeDao.getTheme(themeName);
	}
	
	public List<Theme> getAllThemes(){
		return themeDao.getAllThemes();
	}
	
	public void saveTheme(Theme theme) {
		themeDao.saveTheme(theme);
	}
	
	public void updateTheme(Theme theme) {
		themeDao.updateTheme(theme);
	}
	
	public void deleteTheme(String themeName) {
		themeDao.deleteTheme(themeName);
	}

}
