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
		if (charTyped === "Tab") {
			if (e.shiftKey) {
				e.preventDefault();
				self.getTest();
			}
			return;
		}

		if (self.text.length === 0 || self.finished || charTyped === "Shift") return;

		if (self.startTime === undefined) {
			self.startTime = Date.now();
			self.interval = setInterval(self.updateTime, 1000);
		}

		if (charTyped === "Backspace") {
			self.currentCharIndex !== 0 && self.currentCharIndex--;
			let char = self.text[self.currentCharIndex];
			char.timeTyped = undefined;
			char.class = "notTyped";
			char.typed = "";
			char.extra && self.text.splice(self.currentCharIndex, 1);
		} else {
			let char = self.text[self.currentCharIndex];
			if (char.character === ' ' && charTyped !== char.character) {
				self.text.splice(self.currentCharIndex, 0, { character: charTyped, typed: ' ', timeTyped: (Date.now() - self.startTime) / 1000, class: 'error', extra: true });
			} else {
				char.timeTyped = (Date.now() - self.startTime) / 1000;
				char.typed = charTyped;
				if (char.typed !== char.character) {
					char.class = 'error';
					self.errors.push({ ...char });
				}
				else char.class = 'correct';
			}
			self.currentCharIndex++;
		}


		if (self.currentCharIndex >= self.text.length) {
			self.finished = true;
			self.finishTime = Date.now();
		}
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
				self.text.push({ character: c, typed: "", timeTyped: undefined, class: 'notTyped', extra: false });
			}
			self.author = data.author;
			self.loading = false;
		});
	};

	self.getTest();
}]);