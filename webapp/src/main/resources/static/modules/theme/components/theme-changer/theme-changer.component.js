
angular.module('theme-module').component('themeChanger', {
	controller: ThemeChanger,
	template: '<div><a href="/themes/editor">Themes:</a> <span ng-repeat="theme in $ctrl.availableThemes" ng-click="$ctrl.getTheme(theme)"> {{theme}} </span></div>',
	bindings: {
		currentTheme: '<'
	},
})

ThemeChanger.$inject = ['ThemeChangerService']

function ThemeChanger(ThemeChangerService) {
	var self = this;

	self.currentTheme = "default";
	self.availableThemes = [];

	this.getTheme = getTheme;
	this.updateThemes = updateAvailableThemes;

	init();

	var colors = [
		'--c-accent',
		'--c-accent-light',
		'--c-bg-secondary',
		'--c-bg-main',
		'--c-neutral',
		'--c-not-typed',
		'--c-error',
		'--c-correct'
	]

	function init() {
		let themeJSON = window.localStorage.getItem("theme");
		if (themeJSON) {
			let theme = JSON.parse(themeJSON);
			let root = document.querySelector(":root");
			for (let color in colors) {
				root.style.setProperty(color, theme[color]);
			}
		}
		updateAvailableThemes();
	}

	function getTheme(themeName) {
		ThemeChangerService.getTheme(themeName).then((theme) => {
			self.currentTheme = theme.name;

			let root = document.querySelector(":root");
			for (let color in colors) {
				root.style.setProperty(color, theme[color]);
			}
			window.localStorage.setItem("theme", JSON.stringify(theme));
		});
	}

	function updateAvailableThemes() {
		ThemeChangerService.getAvailableThemes().then((res) => {
			self.availableThemes = res;
		});
	}
}