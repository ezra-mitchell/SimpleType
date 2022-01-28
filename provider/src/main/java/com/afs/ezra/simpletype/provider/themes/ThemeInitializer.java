package com.afs.ezra.simpletype.provider.themes;

import java.awt.Color;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.afs.ezra.simpletype.provider.themes.model.ThemeImpl;
import com.afs.ezra.simpletype.provider.themes.repo.ThemeRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ThemeInitializer implements CommandLineRunner{
	
	private final ThemeRepository themeRepository;
	
	@Override
	public void run(String... args) throws Exception {
		ThemeImpl theme = new ThemeImpl();
		
		theme.setName("default");
		theme.setAccent(new Color(15229522));
		theme.setAccentLight(new Color(15236769));
		theme.setBackgroundMain(new Color(15657936));
		theme.setBackgroundSecondary(new Color(15525820));
		theme.setCorrect(new Color(15607415));
		theme.setError(new Color(3934489));
		theme.setNeutral(new Color(6840180));
		theme.setNotTyped(new Color(5209260));
		
		themeRepository.save(theme);
	}	

}
