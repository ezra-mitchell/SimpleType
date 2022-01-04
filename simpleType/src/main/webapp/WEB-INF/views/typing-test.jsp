<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SimpleType</title>
<script src="/resources/angular.min.js"></script>
<script src="/resources/controllers/typing-test.js"></script>
<link rel="stylesheet" href="/resources/style.css">
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

.typing-pane {
	margin-left: auto;
}

*:focus {
	outline: none;
}

.typing-container {
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
}

.sub-text {
	color: blue;
	font-size: 0.7em;
}
</style>
</head>
<body ng-app="typing-test">
	<jsp:include page="../fragments/header.jsp"></jsp:include>

	<div ng-controller="TypingTestController" ng-init="getTest()"
		class="typing-container">
		<div>
			<h2>{{currentTime}}</h2>
			<div class="typing-pane" ng-keydown="onKeyPress($event)"
				tabindex="-1" autofocus>
				<span ng-repeat="charObj in text" ng-class="charObj.class">{{charObj.character}}</span>
			</div>
			<form action="/post-leaderboard" method="post">
				<label for="name" ng-if-start="finished === true">Name:</label> <input
					type="text" name="name" required id="name"> <label
					for="age">Age:</label> <input type="number" name="age" id="age">
				<input type="hidden" name="text[]" value="{{text}}"> <input
					type="hidden" name="errors[]" value="{{errors}}"> <input
					type="submit" value="Submit to leaderboard" ng-if-end>
			</form>
			
			<p class="sub-text">Shift Tab for new test</p>
		</div>
	</div>


</body>
</html>