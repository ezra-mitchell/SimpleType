package com.afs.ezra.simpletype.webapp.themes;

import java.util.List;
import java.util.stream.Collectors;

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
	public ResponseEntity<List<String>> getAvailableThemes() {
		return ResponseEntity.ok(manager.getAllThemes().stream().map((theme) -> theme.getName()).collect(Collectors.toList()));
	}

	@DeleteMapping("/themes/{themeName}")
	public ResponseEntity<Void> deleteTheme(@PathVariable String themeName) {
		manager.deleteTheme(themeName);
		return ResponseEntity.ok(null);
	}

	@PostMapping("/themes")
	public ResponseEntity<Void> createTheme(@RequestBody @Valid ThemeDto themeDto) {
		manager.saveTheme(themeDto);
		return ResponseEntity.ok(null);
	}

	@PutMapping("/themes")
	public ResponseEntity<Void> updateTheme(@RequestBody @Valid ThemeDto themeDto) {
		manager.updateTheme(themeDto);
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("/themes/editor")
	public ModelAndView themeEditor() {
		return new ModelAndView("theme-editor");
	}

}
