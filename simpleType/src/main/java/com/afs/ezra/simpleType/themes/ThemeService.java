package com.afs.ezra.simpleType.themes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ThemeService {
	
	private final ThemeManager manager;
	
	public ThemeDto getTheme(String themeName) {
		return convertToDTO(manager.getTheme(themeName));
	}
	
	public List<String> getAvaialableThemes() {
		return manager.getAllThemes().stream().map((theme) -> theme.getName()).collect(Collectors.toList());
	}
	
	public void updateTheme(ThemeDto themeDto) {
		Theme theme = convertToEntity(themeDto);
		manager.updateTheme(theme);
	}
	
	public void saveTheme(ThemeDto themeDto) {
		manager.saveTheme(convertToEntity(themeDto));
	}
	
	public void deleteTheme(String themeName) {
		manager.deleteTheme(themeName);
	}
	
	
	private ThemeDto convertToDTO(Theme theme) {
		ThemeDto dto = new ThemeDto();
		
		dto.setName(theme.getName());
		dto.setId(theme.getId());
		dto.setColors(new ThemeColorsDto());
		dto.getColors().setAccent(Integer.toHexString(theme.getAccent()));
		dto.getColors().setAccentLight(Integer.toHexString(theme.getAccentLight()));
		dto.getColors().setBackgroundMain(Integer.toHexString(theme.getBackgroundMain()));
		dto.getColors().setBackgroundSecondary(Integer.toHexString(theme.getBackgroundSecondary()));
		dto.getColors().setNeutral(Integer.toHexString(theme.getNeutral()));
		dto.getColors().setCorrect(Integer.toHexString(theme.getCorrect()));
		dto.getColors().setError(Integer.toHexString(theme.getError()));
		dto.getColors().setNotTyped(Integer.toHexString(theme.getNotTyped()));
		
		return dto;
	}
	
	private Theme convertToEntity(ThemeDto dto) {
		Theme theme = new Theme();
		
		theme.setId(dto.getId());
		theme.setName(dto.getName());
		theme.setAccent(parseHexString(dto.getColors().getAccent()));
		theme.setAccentLight(parseHexString(dto.getColors().getAccentLight()));
		theme.setBackgroundMain(parseHexString(dto.getColors().getBackgroundMain()));
		theme.setBackgroundSecondary(parseHexString(dto.getColors().getBackgroundSecondary()));
		theme.setNeutral(parseHexString(dto.getColors().getNeutral()));
		theme.setCorrect(parseHexString(dto.getColors().getCorrect()));
		theme.setError(parseHexString(dto.getColors().getError()));
		theme.setNotTyped(parseHexString(dto.getColors().getNotTyped()));

		return theme;
	}
	
	private Integer parseHexString(String hex) {
		return Integer.decode("0x" + hex.replaceAll("#", ""));
	}
	

}
