'use strict';

angular.module('typing-test').controller('TypingTestController', ['TypingTestService', function(typingTestService) {
	var self = this;

	self.text = []; // { char: '', typed, '', timeTyped: undefined }
	self.loading = true;
	self.currentCharIndex = 0;
	self.startTime = undefined;
	self.finishTime = undefined;
	self.currentTime = undefined;
	self.finished = false;
	self.interval = undefined;
	self.errors = []; // { character: '', typed, '', timeTyped: undefined }
	self.author = "";

	self.onKeyPress = function(e) {

		let charTyped = e.key;
		switch (charTyped) {
			case "Tab":
				if (e.shiftKey) {
					e.preventDefault();
					self.getTest();
				}
			case "Shift":
				return;
			case "Backspace":
				if (self.text.length === 0 || self.finished) return;
				backspace();
				break;
			default:
				if (self.text.length === 0 || self.finished) return;
				initializeStartTime();
				typeCharacter(charTyped);
		}

		checkFinished();
	};

	self.updateTime = function() {
		if (self.finished) {
			self.interval && clearInterval(self.interval);
			return;
		}

		self.currentTime = Math.round((Date.now() - self.startTime) / 1000);
	};

	self.getTest = function() {
		self.loading = true;
		self.startTime = undefined;
		self.currentTime = 0;
		self.finished = false;
		self.finishTime = undefined;
		self.currentCharIndex = 0;
		self.errors = [];
		self.text = [];

		self.interval && clearInterval(self.interval);
		self.interval = undefined;


		typingTestService.getTypingTest().then(data => {
			for (let c of data.content) {
				self.text.push({ character: c, typed: "", timeTyped: undefined, class: 'notTyped', deletable: false });
			}
			self.author = data.author;
			self.loading = false;
		});
	};

	function initializeStartTime() {
		if (self.startTime === undefined) {
			self.startTime = Date.now();
			self.interval = setInterval(self.updateTime, 1000);
		}
	}

	function backspace() {
		self.currentCharIndex !== 0 && self.currentCharIndex--;
		let char = self.text[self.currentCharIndex];
		char.timeTyped = undefined;
		char.class = "notTyped";
		char.typed = "";
		char.deletable === true && self.text.splice(self.currentCharIndex, 1);
	}

	function typeCharacter(charTyped) {
		let currentCharacterObject = self.text[self.currentCharIndex];
		let currentChar = currentCharacterObject.character;
		let correct = charTyped === currentChar;

		let timeTyped = (Date.now() - self.startTime) / 1000;

		if (currentChar === ' ' && !correct)
			self.text.splice(self.currentCharIndex, 0, { character: charTyped, typed: '\0', timeTyped: timeTyped / 1000, class: 'error', deletable: true })
		else {
			currentCharacterObject.timeTyped = timeTyped;
			currentCharacterObject.typed = charTyped;
			currentCharacterObject.class = correct ? 'correct' : 'error';
			if (!correct) {
				self.errors.push(Object.assign({}, currentCharacterObject));
			}
		}


		self.currentCharIndex++;

	}

	function checkFinished() {
		if (self.currentCharIndex >= self.text.length) {
			self.finished = true;
			self.finishTime = Date.now();
		}
	}



	self.getTest();
}]);