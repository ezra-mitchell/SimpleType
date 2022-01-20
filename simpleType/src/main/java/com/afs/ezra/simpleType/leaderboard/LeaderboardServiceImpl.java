package com.afs.ezra.simpleType.leaderboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.afs.ezra.simpleType.leaderboard.model.LeaderboardPlace;
import com.afs.ezra.simpleType.leaderboard.model.TypedCharacter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeaderboardServiceImpl implements LeaderboardService{

	private final ObjectMapper objectMapper;

	@Override
	public List<LeaderboardPlace> postLeaderboardScore(String textJSON, String errorsJSON, String name, int age) throws JsonParseException, JsonMappingException, IOException {
		TypedCharacter[] text = objectMapper.readValue(textJSON, TypedCharacter[].class);
		TypedCharacter[] errors = objectMapper.readValue(errorsJSON, TypedCharacter[].class);
		
		//TODO (extra features) validate text and errors
		//calculating speed, by convention a "word" is any five characters
		
		double testLengthMinutes = text[text.length - 1].getTimeTyped() / 60;
		
		int errorsWithCorrection = (int) Arrays.stream(text).filter((typedChar) -> typedChar.getCharacter() != typedChar.getTyped()).count();

		double netWPM = ((text.length / 5) - errorsWithCorrection) / testLengthMinutes;
		
		double accuracy = (1- (double)errors.length / (double)text.length ) * 100;
		
		
		//TODO (for task 2) do real placements with database
		List<LeaderboardPlace> placement = new ArrayList<LeaderboardPlace>();
		placement.add(new LeaderboardPlace(1, name, netWPM, accuracy));
		return placement;
	}

	@Override
	public List<LeaderboardPlace> getTopLeaderboard(int length) {
		//TODO get real placements from database
		List<LeaderboardPlace> placement = new ArrayList<LeaderboardPlace>();
		placement.add(new LeaderboardPlace(1, "Example name 1", 120.4, 95.3));
		placement.add(new LeaderboardPlace(2, "Example name 2", 119.2, 95.3));
		return placement;
	}

}
