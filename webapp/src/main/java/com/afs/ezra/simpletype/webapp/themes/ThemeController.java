package com.afs.ezra.simpletype.webapp.themes;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ThemeController {

	private final ThemeManager manager;

	@GetMapping("/themes/{themeName}")
	public ResponseEntity<ThemeDto> getTheme(@PathVariable String themeName) {
		return manager.getTheme(themeName);
	}

	@GetMapping("/themes/list")
	public ResponseEntity<String[]> getAvailableThemes() {
		return manager.getAllThemes();
	}

	@DeleteMapping("/themes/{themeName}")
	public ResponseEntity<Void> deleteTheme(@PathVariable String themeName) {
		return manager.deleteTheme(themeName);
	}

	@PostMapping("/themes")
	public ResponseEntity<Void> createTheme(@RequestBody @Valid ThemeDto themeDto) {
		return manager.saveTheme(themeDto);
	}

	@PutMapping("/themes")
	public ResponseEntity<Void> updateTheme(@RequestBody @Valid ThemeDto themeDto) {
		return manager.updateTheme(themeDto);
	}
	
	@GetMapping("/themes/editor")
	public ModelAndView themeEditor() {
		return new ModelAndView("theme-editor");
	}

}
