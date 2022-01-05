<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SimpleType</title>
<script src="/resources/angular.min.js"></script>
<script src="/resources/modules/typing-test/typing-test.module.js"></script>
<script src="/resources/modules/typing-test/services/typing-test.service.js"></script>
<script src="/resources/modules/typing-test/controllers/typing-test.controller.js"></script>
<link rel="stylesheet" href="/resources/style.css">
<!-- I know its best practice to have this in a separate file this is just convenient while the HTML file is small -->
<style>
.correct {
	color: var(--c-pink-dark);
}

.error {
	color: var(--c-red-dark);
}

.notTyped {
	color: var(--c-blue) !important;
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
	color: var(--c-blue) !important;
}


form {
	display: flex;
	flex-direction: column;
	gap: 1em;
	align-items: center;
}

input {
	width: 300px;
	border-radius: 5px;
	background-color: var(--c-tan-light);
	color: var(--c-pink-dark);
	border: 1px solid var(--c-pink-dark);
	padding: 0.5em;
	transition: width 0.1s ease-in-out;
}

input::placeholder {
	color: var(--c-pink-dark);
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

.skeleton-text {
	background-color: lightgray;
	width: 60vw;
	height: 25px;
	display: block;
}

.dNone {
	display: none;
}
</style>
</head>
<body ng-app="typing-test">
	<jsp:include page="../fragments/header.jsp"></jsp:include>

	<div ng-controller="TypingTestController as ctrl"
		class="typing-container">
		<div>
			<h2>{{ctrl.currentTime}}</h2>
			<div class="typing-pane" ng-keydown="ctrl.onKeyPress($event)" ng-cloak
				tabindex="-1" autofocus>
				<span class="skeleton-text" ng-show="ctrl.loading" > </span>	
				<span ng-repeat="charObj in ctrl.text" ng-class="charObj.class">{{charObj.character}}</span>
			</div>
			<p class="author" align="right">{{ctrl.author}}</p>

			<p class="sub-text" ng-click="ctrl.getTest()">Shift Tab for new test</p>

			<form action="/post-leaderboard" method="post">
				<input type="text" name="name" required id="name" placeholder="Name" ng-class="{closed: ctrl.finished === false}">
				<input type="number" name="age" id="age" placeholder="Age" ng-class="{closed: ctrl.finished === false}">
				<input type="hidden" name="text[]" value="{{ctrl.text}}" ng-if-start="ctrl.finished === true"> 
				<input type="hidden" name="errors[]" value="{{ctrl.errors}}" ng-if-end> 
				<input type="submit" value="Submit to leaderboard" ng-class="{closed: ctrl.finished === false}">
			</form>

		</div>
	</div>


</body>
</html>