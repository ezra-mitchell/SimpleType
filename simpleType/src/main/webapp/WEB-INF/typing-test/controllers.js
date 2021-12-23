var module = angular.module('typing-test', []);


module.controller('TypingTestController', function($scope) {
	$scope.text = []; // { char: '', typed, '', timeTyped: undefined }
	$scope.currentCharIndex = 0;
	$scope.started = false;
	$scope.startTime = undefined;
	$scope.finishTime = undefined;
	$scope.finished = false;
	$scope.interval = undefined;
	$scope.errors = []; // { char: '', typed, '', timeTyped: undefined }

	$scope.keyPress = function(e) {
		if (startTime === undefined) {
			$scope.startTime = new Date().getMilliseconds();
			$scope.interval = setInterval($scope.updateTime, 1000);
		}
		if ($scope.text.length === 0) return;

		let charTyped = e.key;

		if (charTyped === "Backspace") {
			//check for ctrl
			$scope.currentCharIndex--;
			let char = $scope.text[$scope.currentCharIndex];
			char.timeTyped = undefined;
			char.typed = '';
		} else {
			let char = $scope.text[$scope.currentCharIndex];
			char.timeTyped = new Date().getMilliseconds();
			char.typed = charTyped;
			$scope.currentCharIndex++;
		}

		if ($scope.currentCharIndex >= $scope.text.length) {
			$scope.finished = true;
			$scope.finishTime = new Date().getMilliseconds();
		}
	}

	$scope.updateTime = function() {
		if ($scope.finished) {
			$scope.interval && clearInterval($scope.interval);
			return;
		}


	}
})