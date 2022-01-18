
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SimpleType</title>
<script src="/resources/angular.min.js"></script>
<script src="/resources/modules/theme/theme.module.js"></script>
<script src="/resources/modules/theme/services/theme-changer.service.js"></script>
<script
	src="/resources/modules/theme/controllers/theme-editor.controller.js"></script>
<link rel="stylesheet" href="/resources/style.css">
</head>
<body ng-app="theme">
	<jsp:include page="../fragments/header.jsp"></jsp:include>

	<div ng-controller="ThemeEditorController as ctrl">
		<form ng-submit="ctrl.stubSubmit($event)">
			<label>Name: <input type="text" ng-model="ctrl.theme.name" /></label>
			<h4>Colors</h4>
			<label>Background Main: <input type="text"
				ng-model="ctrl.theme.colors['--c-bg-main']" /></label> <label>Background
				Secondary: <input type="text"
				ng-model="ctrl.theme.colors['--c-bg-secondary']" />
			</label> <label>Accent: <input type="text"
				ng-model="ctrl.theme.colors['--c-accent']" /></label> <label>Accent
				Light: <input type="text"
				ng-model="ctrl.theme.colors['--c-accent-light']" />
			</label> <label>Neutral: <input type="text"
				ng-model="ctrl.theme.colors['--c-neutral']" /></label> <label>Not
				Typed: <input type="text"
				ng-model="ctrl.theme.colors['--c-not-typed']" />
			</label> <label>Error: <input type="text"
				ng-model="ctrl.theme.colors['--c-error']" />
			</label> <label>Correct: <input type="text"
				ng-model="ctrl.theme.colors['--c-correct']" /></label>

		</form>
		<button ng-click="ctrl.createTheme()">Create</button>
		<button ng-click="ctrl.updateTheme()">Update</button>
		<button ng-click="ctrl.deleteTheme()">Delete</button>
		<button ng-click="ctrl.updateAvailableThemes()">Refresh</button>
		<br>
		<span ng-repeat="theme in ctrl.availableThemes"
			ng-click="ctrl.getTheme(theme)">{{theme}}</span>
	</div>
</body>
</html>