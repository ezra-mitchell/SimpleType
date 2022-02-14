package com.afs.ezra.simpletype.provider.themes.service;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Color;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.afs.ezra.simpletype.provider.common.HttpException;
import com.afs.ezra.simpletype.provider.themes.model.ThemeImpl;
import com.afs.ezra.simpletype.provider.themes.model.ThemeView;
import com.afs.ezra.simpletype.provider.themes.repo.ThemeRepository;

@SpringBootTest(classes = { ThemeService.class, ThemeMocks.class })
@ActiveProfiles("test")
class ThemeServiceTest extends AbstractTestNGSpringContextTests {

	@Autowired
	ThemeRepository themeRepository;

	@Autowired
	ThemeService themeService;
	
	@BeforeMethod
	public void beforeAllTests() {
		reset(themeRepository);
	}

	@Test
	public void getThemeWithValidName() throws HttpException {
		ThemeImpl theme = createSampleThemeImpl();
		when(themeRepository.findThemeByName("name")).thenReturn(Optional.of(theme));

		ThemeView fromService = themeService.getTheme("name");
		Assert.assertEquals(ThemeImpl.convert(fromService), theme);

	}

	@Test
	public void getThemeWithInvalidName() {
		when(themeRepository.findThemeByName("name")).thenReturn(Optional.empty());
		Assert.assertThrows(HttpException.class, () -> themeService.getTheme("name"));

	}

	@Test
	public void updateValidTheme() throws HttpException {
		themeService.updateTheme(ThemeView.convert(createSampleThemeImpl()));
		verify(themeRepository).save(createSampleThemeImpl());
	}
	
	public void updateInvalidTheme() throws HttpException {
		ThemeView view = ThemeView.convert(createSampleThemeImpl());
		view.setId(null);
		Assert.assertThrows(HttpException.class, () -> themeService.updateTheme(view));
	}

	@Test
	public void saveValidTheme() {
		themeService.saveTheme(ThemeView.convert(createSampleThemeImpl()));
		verify(themeRepository).save(createSampleThemeImpl());
	}

	@Test
	public void deleteValidTheme() {
		themeService.deleteTheme("name");
		verify(themeRepository).deleteThemeByName("name");
	}

	public ThemeImpl createSampleThemeImpl() {
		ThemeImpl theme = new ThemeImpl();
		theme.setId(1L);
		theme.setName("name");
		theme.setBackgroundMain(new Color(0));
		theme.setBackgroundSecondary(new Color(0));
		theme.setAccent(new Color(0));
		theme.setAccentLight(new Color(0));
		theme.setCorrect(new Color(0));
		theme.setError(new Color(0));
		theme.setNeutral(new Color(0));
		theme.setNotTyped(new Color(0));
		return theme;
	}

}
