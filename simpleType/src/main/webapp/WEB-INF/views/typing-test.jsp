<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SimpleType</title>
<script src="/resources/angular.min.js"></script>
<script src="/resources/controllers/typing-test.js"></script>
<style>
.correct {
	color: rgb(0, 255, 0);
}

.error {
	color: red;
}

.notTyped {
	color: gray !important;
}
</style>
</head>
<body ng-app="typing-test">
	<jsp:include page="../fragments/header.jsp"></jsp:include>

	<div ng-controller="TypingTestController" ng-init="getTest()"
		ng-keydown="onKeyPress($event)" tabindex="-1">
		<h2>{{currentTime}}</h2>
		<span ng-repeat="charObj in text"
			ng-class="charObj.class">{{charObj.char}}</span>
	</div>

</body>
</html>