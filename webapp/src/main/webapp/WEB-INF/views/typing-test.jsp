<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SimpleType</title>
<script src="/resources/angular.min.js"></script>
<script src="/resources/modules/theme/theme.module.js"></script>
<script src="/resources/modules/typing-test/typing-test.module.js"></script>
<script src="/resources/modules/theme/services/theme-changer.service.js"></script>
<script src="/resources/modules/theme/components/theme-changer/theme-changer.component.js"></script>
<script src="/resources/modules/typing-test/services/typing-test.service.js"></script>
<script src="/resources/modules/typing-test/controllers/typing-test.controller.js"></script>
<link rel="stylesheet" href="/resources/style.css">
<!-- I know its best practice to have this in a separate file this is just convenient while the HTML file is small -->
<style>
.correct {
	color: var(--c-correct);
}

.error {
	color: var(--c-error);
}

.notTyped {
	color: var(--c-not-typed);
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
	color: var(--c-accent);
	font-size: 0.7em;
	text-decoration: underline;
	cursor: pointer;
}

.author {
	color: var(--c-not-typed) !important;
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
	background-color: var(--c-bg-main);
	color: var(--c-correct);
	border: 1px solid var(--c-correct);
	padding: 0.5em;
	transition: width 0.1s ease-in-out;
}

input::placeholder {
	color: var(--c-correct);
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
	background-color: var(--c-error);
}

.skeleton-text {
	background-color: var(--c-neutral);
	width: 60vw;
	height: 25px;
	display: block;
}

.dNone {
	display: none;
}
</style>
</head>
<body ng-app="typing-test-module">
	<jsp:include page="../fragments/header.jsp"></jsp:include>

	<div ng-controller="TypingTestController as ctrl"
		class="typing-container">
		<div>
			<h2>{{ctrl.currentTime}}</h2>
			<div class="typing-pane" ng-keydown="ctrl.onKeyPress($event)" ng-cloak
				tabindex="-1" id="typing-test" autofocus >
				<span class="skeleton-text" ng-show="ctrl.loading" > </span>	
				<span ng-repeat="charObj in ctrl.text" ng-class="charObj.class" class="test-char">{{charObj.character}}</span>
			</div>
			<p class="author" align="right">{{ctrl.author}}</p>

			<p class="sub-text" ng-click="ctrl.getTest()">Shift Tab for new test</p>

			<form action="/leaderboard" method="post" id="test-form">
				<input type="text" name="name" required id="name" placeholder="Name" ng-class="{closed: ctrl.finished === false}">
				<input type="number" name="age" id="age" placeholder="Age" ng-class="{closed: ctrl.finished === false}">
				<input type="hidden" name="text[]" value="{{ctrl.text}}" ng-if-start="ctrl.finished === true"> 
				<input type="hidden" name="errors[]" value="{{ctrl.errors}}" ng-if-end> 
				<input type="submit" value="Submit to leaderboard" ng-class="{closed: ctrl.finished === false}" id="test-form-submit">
			</form>

		</div>
	</div>

	<theme-changer></theme-changer>


</body>
</html>