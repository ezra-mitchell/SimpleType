package com.afs.ezra.simpletype.provider.leaderboard.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.afs.ezra.simpletype.provider.common.HttpException;
import com.afs.ezra.simpletype.provider.leaderboard.model.LeaderboardPlaceImpl;
import com.afs.ezra.simpletype.provider.leaderboard.model.LeaderboardPlaceView;
import com.afs.ezra.simpletype.provider.leaderboard.model.TypedCharacter;
import com.afs.ezra.simpletype.provider.leaderboard.model.TypingTest;
import com.afs.ezra.simpletype.provider.leaderboard.repo.LeaderboardRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeaderboardServiceImpl implements LeaderboardService {

	private final LeaderboardRepository leaderboard;

	@Override
	public LeaderboardPlaceView postLeaderboardScore(TypingTest testData) throws HttpException {

		ObjectMapper mapper = new ObjectMapper();

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		TypedCharacter[] text;
		TypedCharacter[] errors;

		try {
			text = mapper.readValue(testData.getText(), TypedCharacter[].class);
			errors = mapper.readValue(testData.getErrors(), TypedCharacter[].class);

		} catch (JsonProcessingException e) {
			throw new HttpException(HttpStatus.BAD_REQUEST);
		}

		double testLengthMinutes = text[text.length - 1].getTimeTyped() / 60;

		int errorsWithCorrection = (int) Arrays.stream(text)
				.filter((typedChar) -> typedChar.getCharacter() != typedChar.getTyped()).count();

		double netWPM = ((text.length / 5) - errorsWithCorrection) / testLengthMinutes;

		double accuracy = (1 - (double) errors.length / (double) text.length) * 100;

		LeaderboardPlaceImpl place = leaderboard
				.save(new LeaderboardPlaceImpl(null, testData.getName(), netWPM, accuracy));

		LeaderboardPlaceView view = LeaderboardPlaceView.convert(place);
		view.setPlace(leaderboard.getPlacement(place) + 1L);

		return view;
	}

	@Override
	public List<LeaderboardPlaceView> getTopLeaderboard(int length) throws HttpException {
		if(length <= 0) throw new HttpException(HttpStatus.BAD_REQUEST);
		List<LeaderboardPlaceImpl> topPlacements = leaderboard.getTopLeaderboard(PageRequest.of(0, length));

		return IntStream.range(0, topPlacements.size()).mapToObj((i) -> {
			LeaderboardPlaceImpl place = topPlacements.get(i);
			return new LeaderboardPlaceView((long) i + 1, place.getName(), place.getSpeed(), place.getAccuracy());
		}).collect(Collectors.toList());
	}

}
