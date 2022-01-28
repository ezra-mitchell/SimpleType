
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SimpleType</title>
<script src="/resources/angular.min.js"></script>
<script src="/resources/modules/theme/theme.module.js"></script>
<script src="/resources/modules/theme/services/theme-changer.service.js"></script>
<script src="/resources/modules/theme/components/theme-changer/theme-changer.component.js"></script>
<script
	src="/resources/modules/theme/controllers/theme-editor.controller.js"></script>
<link rel="stylesheet" href="/resources/style.css">
</head>
<body ng-app="theme-module">
	<jsp:include page="../fragments/header.jsp"></jsp:include>

	<div ng-controller="ThemeEditorController as ctrl">
		<form ng-submit="ctrl.stubSubmit($event)">
			<label>Name: <input type="text" ng-model="ctrl.theme.name" ></label>
			<h4>Colors</h4>
			<label>Background Main: <input type="text"
				ng-model="ctrl.theme['--c-bg-main']" /></label> <label>Background
				Secondary: <input type="text"
				ng-model="ctrl.theme['--c-bg-secondary']" />
			</label> <label>Accent: <input type="text"
				ng-model="ctrl.theme['--c-accent']" /></label> <label>Accent
				Light: <input type="text"
				ng-model="ctrl.theme['--c-accent-light']" />
			</label> <label>Neutral: <input type="text"
				ng-model="ctrl.theme['--c-neutral']" /></label> <label>Not
				Typed: <input type="text"
				ng-model="ctrl.theme['--c-not-typed']" />
			</label> <label>Error: <input type="text"
				ng-model="ctrl.theme['--c-error']" />
			</label> <label>Correct: <input type="text"
				ng-model="ctrl.theme['--c-correct']" /></label>

		</form>
		<button ng-click="ctrl.createTheme()">Create</button>
		<button ng-click="ctrl.updateTheme()">Update</button>
		<button ng-click="ctrl.deleteTheme()">Delete</button>
		<button ng-click="ctrl.updateAvailableThemes()">Get Themes</button>
		<br>
		<span ng-repeat="theme in ctrl.availableThemes"
			ng-click="ctrl.getTheme(theme)"> {{theme}} </span>
			
		<br>
		<br>
		<theme-changer></theme-changer>
	</div>
</body>
</html>