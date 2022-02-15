package com.afs.ezra.simpletype.webapp.typing;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.ArgumentCaptor;
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
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.afs.ezra.simpletype.webapp.leaderboard.LeaderboardManager;
import com.afs.ezra.simpletype.webapp.leaderboard.LeaderboardPlaceView;
import com.afs.ezra.simpletype.webapp.leaderboard.TypingTest;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class TypingControllerTest extends AbstractTestNGSpringContextTests {

	private WebDriver driver;

	@Autowired
	LeaderboardManager leaderboardManager;

	@Autowired
	TypingService typingService;

	@Value("${test.baseUrl}")
	private String baseUrl;

	@BeforeSuite
	public void beforeTest() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		driver = new ChromeDriver(options);
	}

	@AfterSuite
	public void afterTest() {
		driver.close();
	}

	@Test
	void pageGetsTypingTest() {
		when(typingService.getTypingTest()).thenReturn(new TypingTestModel("", new String[] {}, "words", "", "", 5));
		driver.get(baseUrl + "/home");

		WebDriverWait wait = new WebDriverWait(driver, 1);

		wait.until(ExpectedConditions.numberOfElementsToBe(By.className("test-char"), 5));
	}

	@Test
	void formAppearsAfterTypingTest() {
		when(typingService.getTypingTest()).thenReturn(new TypingTestModel("", new String[] {}, "words", "", "", 5));
		driver.get(baseUrl + "/home");

		driver.findElement(By.id("typing-test")).sendKeys("words");

		WebDriverWait wait = new WebDriverWait(driver, 1);

		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("test-form-submit")));

	}

	@Test
	void canSubmitForm() {
		when(typingService.getTypingTest()).thenReturn(new TypingTestModel("", new String[] {}, "words", "", "", 5));
		driver.get(baseUrl + "/home");

		driver.findElement(By.id("typing-test")).sendKeys("words");

		WebDriverWait wait = new WebDriverWait(driver, 1);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name")));

		LeaderboardPlaceView view = new LeaderboardPlaceView();
		view.setAccuracy(99.9);
		view.setSpeed(60.0);
		view.setName("name");
		view.setPlace(1L);

		when(leaderboardManager.postLeaderboardScore(any())).thenReturn(view);
		when(leaderboardManager.getTopLeaderboard(10)).thenReturn(new LeaderboardPlaceView[] { view });

		driver.findElement(By.id("name")).sendKeys("name");
		driver.findElement(By.id("test-form-submit")).click();

		ArgumentCaptor<TypingTest> testCaptor = ArgumentCaptor.forClass(TypingTest.class);
		verify(leaderboardManager, times(1)).postLeaderboardScore(testCaptor.capture());
		
		
		wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("wpm")), "60 wpm"));
		wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("accuracy")), "99.9%"));
		wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("place")), "1"));
	}

	@Test
	void formNameRequired() {
		reset(leaderboardManager);
		when(typingService.getTypingTest()).thenReturn(new TypingTestModel("", new String[] {}, "words", "", "", 5));
		driver.get(baseUrl + "/home");
		driver.findElement(By.id("typing-test")).sendKeys("words");
		WebDriverWait wait = new WebDriverWait(driver, 1);

		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("test-form")));

		verify(leaderboardManager, times(0)).postLeaderboardScore(any());
	}

}
