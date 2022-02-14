package com.afs.ezra.simpletype.provider.themes.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.afs.ezra.simpletype.provider.common.HttpException;
import com.afs.ezra.simpletype.provider.themes.model.ThemeImpl;
import com.afs.ezra.simpletype.provider.themes.model.ThemeView;
import com.afs.ezra.simpletype.provider.themes.repo.ThemeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ThemeService {
	
	private final ThemeRepository themeRepository;
	
	public ThemeView getTheme(String themeName) throws HttpException {
		return ThemeView.convert(themeRepository.findThemeByName(themeName).orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND)));
	}
	
	public List<String> getAvaialableThemes() {
		return themeRepository.findAll().stream().map((theme) -> theme.getName()).collect(Collectors.toList());
	}
	
	public void updateTheme(ThemeView themeDto) throws HttpException {
		ThemeImpl theme = ThemeImpl.convert(themeDto);
		if(theme.getId() == null) throw new HttpException(HttpStatus.BAD_REQUEST);
		themeRepository.save(theme);
	}
	
	public void saveTheme(ThemeView themeDto) {
		themeRepository.save(ThemeImpl.convert(themeDto));
	}
	
	@Transactional
	public void deleteTheme(String themeName) {
		themeRepository.deleteThemeByName(themeName);
	}
	
	

}
