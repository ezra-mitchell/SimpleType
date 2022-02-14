package com.afs.ezra.simpletype.webapp.themes;

import static org.mockito.Mockito.when;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class ThemeControllerTest extends AbstractTestNGSpringContextTests {

	private WebDriver driver;
	
	@Autowired
	public ThemeManager manager;
	
	@Value("${test.baseUrl}")
	private String baseUrl;

	@BeforeSuite
	public void beforeTest() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		driver = new ChromeDriver(options);
	}

	@Test
	void webPageExists() {
		driver.get(baseUrl + "/themes/editor");
		Assert.assertTrue(driver.getPageSource().contains("Name:"));
		Assert.assertTrue(driver.getPageSource().contains("Background Main:"));
		Assert.assertTrue(driver.getPageSource().contains("Background Secondary:"));
		Assert.assertTrue(driver.getPageSource().contains("Accent:"));
		Assert.assertTrue(driver.getPageSource().contains("Accent Light:"));
		Assert.assertTrue(driver.getPageSource().contains("Neutral:"));
		Assert.assertTrue(driver.getPageSource().contains("Not Typed:"));
		Assert.assertTrue(driver.getPageSource().contains("Error:"));
		Assert.assertTrue(driver.getPageSource().contains("Correct:"));
	}
	
	@Test
	void canGetThemeList() {
		when(manager.getAllThemes()).thenReturn(new String[] {"themeName"});
		driver.get(baseUrl + "/themes/editor");
		WebDriverWait wait = new WebDriverWait(driver, 5);
		driver.findElement(By.id("get-themes")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("themeList")));
		driver.getPageSource().contains("themeName");
	}
	
	@AfterSuite
	public void afterTest() {
		driver.close();
	}

}