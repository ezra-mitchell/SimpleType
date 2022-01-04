var module = angular.module("typing-test", []);

module.controller("TypingTestController", function($scope, $http) {
	$scope.text = []; // { char: '', typed, '', timeTyped: undefined }
	$scope.currentCharIndex = 0;
	$scope.startTime = undefined;
	$scope.finishTime = undefined;
	$scope.currentTime = undefined;
	$scope.finished = false;
	$scope.interval = undefined;
	$scope.errors = []; // { character: '', typed, '', timeTyped: undefined }
	$scope.author = "";

	$scope.onKeyPress = function(e) {
		let charTyped = e.key;
		if (charTyped === "Tab") {
			if (e.shiftKey) {
				e.preventDefault();
				$scope.getTest();
			}
			return;
		}

		if ($scope.text.length === 0 || $scope.finished || charTyped === "Shift") return;

		if ($scope.startTime === undefined) {
			$scope.startTime = Math.round(Date.now());
			$scope.interval = setInterval($scope.updateTime, 1000);
		}

		if (charTyped === "Backspace") {
			$scope.currentCharIndex !== 0 && $scope.currentCharIndex--;
			let char = $scope.text[$scope.currentCharIndex];
			char.timeTyped = undefined;
			char.class = "notTyped";
			char.typed = "";
			char.extra && $scope.text.splice($scope.currentCharIndex, 1);
		} else {
			let char = $scope.text[$scope.currentCharIndex];
			if (char.character === ' ' && charTyped !== char.character) {
				$scope.text.splice($scope.currentCharIndex, 0, { character: charTyped, typed: ' ', timeTyped: (Date.now() - $scope.startTime) / 1000, class: 'error', extra: true });
			} else {
				char.timeTyped = (Date.now() - $scope.startTime) / 1000;
				char.typed = charTyped;
				if (char.typed !== char.character) {
					char.class = 'error';
					$scope.errors.push({ ...char });
				}
				else char.class = 'correct';
			}
			$scope.currentCharIndex++;
		}


		if ($scope.currentCharIndex >= $scope.text.length) {
			$scope.finished = true;
			$scope.finishTime = Date.now();
		}
	};

	$scope.updateTime = function() {
		if ($scope.finished) {
			$scope.interval && clearInterval($scope.interval);
			return;
		}

		$scope.currentTime = Math.round((Date.now() - $scope.startTime) / 1000);
	};

	$scope.getTest = function() {
		$scope.startTime = undefined;
		$scope.currentTime = 0;
		$scope.finished = false;
		$scope.finishTime = undefined;
		$scope.currentCharIndex = 0;
		$scope.errors = [];
		$scope.text = [];

		$scope.interval && clearInterval($scope.interval);
		$scope.interval = undefined;

		//TODO get test from backend
		$http.get("/test").then((res) => {
			for (let c of res.data.content) {
				$scope.text.push({ character: c, typed: "", timeTyped: undefined, class: 'notTyped', extra: false });
			}
			$scope.author = res.data.author;
		})
	};
});
