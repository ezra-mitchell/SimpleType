'use strict';

angular.module("typing-test").factory('TypingTestService', ['$http', function(http) {
	var factory = {
		getTypingTest: getTypingTest,
	}

	return factory;

	function getTypingTest() {
		return http.get("/test").then((res) => { return res.data });
	}
}]);