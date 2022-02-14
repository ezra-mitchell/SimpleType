package com.afs.ezra.simpletype.provider.leaderboard.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.afs.ezra.simpletype.provider.common.HttpException;
import com.afs.ezra.simpletype.provider.leaderboard.model.LeaderboardPlaceImpl;
import com.afs.ezra.simpletype.provider.leaderboard.model.LeaderboardPlaceView;
import com.afs.ezra.simpletype.provider.leaderboard.model.TypingTest;
import com.afs.ezra.simpletype.provider.leaderboard.repo.LeaderboardRepository;

@SpringBootTest(classes = { LeaderboardService.class, LeaderboardServiceImpl.class, LeaderboardMocks.class })
@ActiveProfiles("test")
class LeaderboardServiceTest extends AbstractTestNGSpringContextTests {

	@Autowired
	LeaderboardRepository leaderboardRepository;

	@Autowired
	LeaderboardService leaderboardService;

	@BeforeMethod
	void beforeMethod() {
		reset(leaderboardRepository);
	}

	@Test
	void getTopLeaderboard_WithValidLength() throws HttpException {
		List<LeaderboardPlaceImpl> topLeaderboard = new ArrayList<LeaderboardPlaceImpl>();
		topLeaderboard.add(new LeaderboardPlaceImpl(1L, "name", 20.0, 20.3));
		when(leaderboardRepository.getTopLeaderboard(any())).thenReturn(topLeaderboard);

		List<LeaderboardPlaceView> view = leaderboardService.getTopLeaderboard(2);
		Assert.assertEquals(view.size(), 1);
		Assert.assertEquals(view.get(0).getName(), topLeaderboard.get(0).getName());
	}

	@Test
	void getTopLeaderboard_WithInvalidLength() {
		Assert.assertThrows(HttpException.class, () -> leaderboardService.getTopLeaderboard(0));
	}
	
	
	@Test
	void postTypingTest_WithValidTest() throws IOException, HttpException {
		when(leaderboardRepository.save(any())).thenAnswer(i -> i.getArgument(0));
		when(leaderboardRepository.getPlacement(any())).thenReturn(1L);

		leaderboardService.postLeaderboardScore(createTypingTest());

		ArgumentCaptor<LeaderboardPlaceImpl> captor = ArgumentCaptor.forClass(LeaderboardPlaceImpl.class);
		verify(leaderboardRepository, times(1)).save(captor.capture());
		
		LeaderboardPlaceImpl place = captor.getValue();
		Assert.assertEquals(place.getName(), "name");
		Assert.assertEquals(place.getAccuracy(), 100.0, 0.1);
		Assert.assertEquals(place.getSpeed(), 60, 0.4);
	}
	
	@Test
	void postTypingTest_WithInvalidTest() {
		TypingTest test = createTypingTest();
		test.setText("[{typed: 0, timeTyped: 'a'}]");
		Assert.assertThrows(HttpException.class, () -> leaderboardService.postLeaderboardScore(test));
		
	}
	
	
	TypingTest createTypingTest() {
		TypingTest test = new TypingTest();
		test.setName("name");
		test.setText("[{\"timeTyped\": 0, \"typed\": \"a\", \"character\": \"a\"},"
				+ "{\"timeTyped\": 0.2, \"typed\": \"a\", \"character\": \"a\"},"
				+ "{\"timeTyped\": 0.6, \"typed\": \"a\", \"character\": \"a\"},"
				+ "{\"timeTyped\": 0.8, \"typed\": \"a\", \"character\": \"a\"},"
				+ "{\"timeTyped\": 1, \"typed\": \"a\", \"character\": \"a\"}]");
		
		test.setErrors("[]");
		return test;
	}

}
