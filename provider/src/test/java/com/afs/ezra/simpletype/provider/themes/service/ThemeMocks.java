package com.afs.ezra.simpletype.provider.themes.service;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.afs.ezra.simpletype.provider.themes.repo.ThemeRepository;

@TestConfiguration
public class ThemeMocks {
	@Bean
	@Primary
	public ThemeRepository themeRepository() {
		return Mockito.mock(ThemeRepository.class);
	}

}
