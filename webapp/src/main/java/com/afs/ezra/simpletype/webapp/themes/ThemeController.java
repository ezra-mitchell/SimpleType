package com.afs.ezra.simpletype.webapp.themes;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
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
	public ResponseEntity<ThemeView> getTheme(@PathVariable String themeName) {
		return ResponseEntity.ok(manager.getTheme(themeName));
	}

	@GetMapping("/themes/list")
	public ResponseEntity<String[]> getAvailableThemes() {
		return ResponseEntity.ok(manager.getAllThemes());
	}

	@DeleteMapping("/themes/{themeName}")
	public ResponseEntity<Void> deleteTheme(@PathVariable String themeName) {
		HttpStatus status = manager.deleteTheme(themeName);
		return new ResponseEntity<Void>(status);
	}

	@PostMapping("/themes")
	public ResponseEntity<Void> createTheme(@RequestBody @Valid ThemeView theme) {
		HttpStatus status = manager.saveTheme(theme);
		return new ResponseEntity<Void>(status);
	}

	@PutMapping("/themes")
	public ResponseEntity<Void> updateTheme(@RequestBody @Valid ThemeView theme) {
		HttpStatus status = manager.updateTheme(theme);
		return new ResponseEntity<Void>(status);
	}

	@GetMapping("/themes/editor")
	public ModelAndView themeEditor() {
		return new ModelAndView("theme-editor");
	}

}
