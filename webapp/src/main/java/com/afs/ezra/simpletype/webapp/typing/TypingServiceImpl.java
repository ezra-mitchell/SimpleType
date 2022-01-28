package com.afs.ezra.simpletype.webapp.typing;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TypingServiceImpl implements TypingService{

	private final RestTemplate http;

	@Override
	public TypingTestModel getTypingTest() {
		return http.getForObject("https://api.quotable.io/random", TypingTestModel.class);
	}
	
}
