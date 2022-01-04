<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SimpleType</title>
<script src="/resources/angular.min.js"></script>
<script src="/resources/controllers/typing-test.js"></script>
<link rel="stylesheet" href="/resources/style.css">
<!-- I know its best practice to have this in a separate file this is just convenient while the HTML file is small -->
<style>
.correct {
	color: var(--c-pink-dark);
}

.error {
	color: var(--c-gray);
}

.notTyped {
	color: var(--c-tan-light) !important;
}

.typing-pane {
	margin-left: auto;
	font-size: 1.5em;
}

*:focus {
	outline: none;
}

.typing-container {
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	padding: 2em;
}

.sub-text {
	color: var(--c-orange);
	font-size: 0.7em;
	text-decoration: underline;
	cursor: pointer;
}

.author {
	color: var(--c-tan-light) !important;
}


form {
	display: flex;
	flex-direction: column;
	gap: 1em;
	align-items: center;
}

input {
	width: 200px;
	border-radius: 5px;
	background-color: var(--c-tan-light);
	color: var(--c-pink-dark);
	border: none;
	padding: 0.5em;
	transition: width 0.1s ease-in-out;
}

input::placeholder {
	color: var(--c-pink-light);
}

.closed {
	width: 0;										
	visibility: hidden;
}

input[type="submit"]{
	cursor: pointer;
	transition: background-color 0.3s ease-in-out;
}

input[type="submit"]:hover{
	background-color: var(--c-red-dark);
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
			<p class="author" align="right">{{author}}</p>

			<p class="sub-text" ng-click="getTest()">Shift Tab for new test</p>

			<form action="/post-leaderboard" method="post">
				<input type="text" name="name" required id="name" placeholder="Name" ng-class="{closed: finished === false}">
				<input type="number" name="age" id="age" placeholder="Age" ng-class="{closed: finished === false}">
				<input type="hidden" name="text[]" value="{{text}}" ng-if-start="finished === true"> 
				<input type="hidden" name="errors[]" value="{{errors}}" ng-if-end> 
				<input type="submit" value="Submit to leaderboard" ng-class="{closed: finished === false}">
			</form>

		</div>
	</div>


</body>
</html>