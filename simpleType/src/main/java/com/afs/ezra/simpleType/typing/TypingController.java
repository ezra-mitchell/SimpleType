package com.afs.ezra.simpleType.typing;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TypingController {
	
	private final TypingService typingService;

	@GetMapping("/")
	public ModelAndView homeRedirect() {
		return new ModelAndView("redirect:/home");
	}
	
	
	@GetMapping("/home")
	public ModelAndView home() {
		return new ModelAndView("typing-test");
	}
	
	
	@GetMapping("/test")
	@ResponseBody
	public ResponseEntity<TypingTestModel> getTest(){
		return ResponseEntity.ok(typingService.getTypingTest());
	}

}
