package com.afs.ezra.simpletype.webapp.themes;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class ThemeMocks {
	
	@Bean
	@Primary
	public ThemeManager themeManager() {
		return Mockito.mock(ThemeManager.class);
	}

}
