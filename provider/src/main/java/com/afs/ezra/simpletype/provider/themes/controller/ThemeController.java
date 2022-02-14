package com.afs.ezra.simpletype.provider.themes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.afs.ezra.simpletype.provider.common.HttpException;
import com.afs.ezra.simpletype.provider.themes.model.ThemeView;
import com.afs.ezra.simpletype.provider.themes.service.ThemeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ThemeController {

	private final ThemeService themeService;

	@GetMapping("/themes/{themeName}")
	public ResponseEntity<ThemeView> getTheme(@PathVariable String themeName) throws HttpException {
		return ResponseEntity.ok(themeService.getTheme(themeName));
	}

	@GetMapping("/themes/list")
	public ResponseEntity<List<String>> getAvailableThemes() {
		return ResponseEntity.ok(themeService.getAvaialableThemes());
	}

	@DeleteMapping("/themes/{themeName}")
	public ResponseEntity<Void> deleteTheme(@PathVariable String themeName) {
		themeService.deleteTheme(themeName);
		return ResponseEntity.ok(null);
	}

	@PostMapping("/themes")
	public ResponseEntity<Void> createTheme(@RequestBody @Valid ThemeView themeDto) {
		themeService.saveTheme(themeDto);
		return ResponseEntity.ok(null);
	}

	@PutMapping("/themes")
	public ResponseEntity<Void> updateTheme(@RequestBody @Valid ThemeView themeDto) throws HttpException {
		themeService.updateTheme(themeDto);
		return ResponseEntity.ok(null);
	}
}
