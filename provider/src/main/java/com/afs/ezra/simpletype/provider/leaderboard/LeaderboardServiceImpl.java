package com.afs.ezra.simpletype.provider.leaderboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeaderboardServiceImpl implements LeaderboardService {

	private final ObjectMapper objectMapper;
	private final LeaderboardRepository leaderboard;

	@Override
	public List<LeaderboardPlaceDTO> postLeaderboardScore(String textJSON, String errorsJSON, String name)
			throws JsonParseException, JsonMappingException, IOException {
		TypedCharacterDTO[] text = objectMapper.readValue(textJSON, TypedCharacterDTO[].class);
		TypedCharacterDTO[] errors = objectMapper.readValue(errorsJSON, TypedCharacterDTO[].class);

		// TODO (extra features) validate text and errors
		// calculating speed, by convention a "word" is any five characters

		double testLengthMinutes = text[text.length - 1].getTimeTyped() / 60;

		int errorsWithCorrection = (int) Arrays.stream(text)
				.filter((typedChar) -> typedChar.getCharacter() != typedChar.getTyped()).count();

		double netWPM = ((text.length / 5) - errorsWithCorrection) / testLengthMinutes;

		double accuracy = (1 - (double) errors.length / (double) text.length) * 100;

		leaderboard.save(new LeaderboardPlace(null, name, netWPM, accuracy));

		List<LeaderboardPlaceDTO> placement = new ArrayList<LeaderboardPlaceDTO>();
		placement.add(new LeaderboardPlaceDTO(1, name, netWPM, accuracy));
		return placement;
	}

	@Override
	public List<LeaderboardPlaceDTO> getTopLeaderboard(int length) {
		List<LeaderboardPlace> topPlacements = leaderboard.getTopLeaderboard(PageRequest.of(0, length));

		return IntStream.range(0, topPlacements.size()).mapToObj((i) -> {
			LeaderboardPlace place = topPlacements.get(i);
			return new LeaderboardPlaceDTO(i + 1, place.getName(), place.getSpeed(), place.getAccuracy());
		}).collect(Collectors.toList());
	}

}
