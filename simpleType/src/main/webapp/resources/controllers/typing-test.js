var module = angular.module("typing-test", []);

module.controller("TypingTestController", ['$scope', function($scope) {
	$scope.text = []; // { char: '', typed, '', timeTyped: undefined }
	$scope.currentCharIndex = 0;
	$scope.startTime = undefined;
	$scope.finishTime = undefined;
	$scope.currentTime = undefined;
	$scope.finished = false;
	$scope.interval = undefined;
	$scope.errors = []; // { char: '', typed, '', timeTyped: undefined }

	$scope.onKeyPress = function(e) {
		if ($scope.text.length === 0 || $scope.finished) return;
		if ($scope.startTime === undefined) {
			$scope.startTime = Date.now() / 1000;
			$scope.interval = setInterval($scope.updateTime, 1000);
		}

		let charTyped = e.key;

		if (charTyped === "Backspace") {
			$scope.currentCharIndex--;
			let char = $scope.text[$scope.currentCharIndex];
			char.timeTyped = undefined;
			char.class = "notTyped";
			char.typed = "";
			console.log(char);
			if (char.extra) $scope.text.splice($scope.currentCharIndex, 1);
		} else {
			let char = $scope.text[$scope.currentCharIndex];
			if (char.char === ' ' && charTyped !== char.char) {
				$scope.text.splice($scope.currentCharIndex, 0, { char: charTyped, typed: ' ', timeTyped: Date.now() / 1000, class: 'error', extra: true });
			} else {
				char.timeTyped = Date.now() / 1000;
				char.typed = charTyped;
				if (char.typed !== char.char) {
					char.class = 'error';
					$scope.errors.push({ ...char });
				}
				else char.class = 'correct';
			}
			$scope.currentCharIndex++;
		}


		//calculate wpm

		if ($scope.currentCharIndex >= $scope.text.length) {
			$scope.finished = true;
			$scope.finishTime = Date.now() / 1000;
		}
	};

	$scope.updateTime = function() {
		if ($scope.finished) {
			$scope.interval && clearInterval($scope.interval);
			return;
		}

		$scope.currentTime = Math.round(Date.now() / 1000 - $scope.startTime);
	};

	$scope.getTest = function() {
		$scope.startTime = undefined;
		$scope.currentTime = 0;
		$scope.finished = false;
		$scope.finishTime = undefined;
		$scope.currentCharIndex = 0;
		$scope.errors = [];
		$scope.text = [];

		//TODO get test from backend
		let testStr = "state ward reliable liver monkey culture excitement provision rub deteriorate ball thick pole deprive ethnic recession plant van relax hotdog";

		for (let c of testStr) {
			$scope.text.push({ char: c, typed: "", timeTyped: undefined, class: 'notTyped', extra: false });
		}
	};
}]);
