
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

	function init() {
		let theme = window.localStorage.getItem("theme");
		if (theme) {
			let colors = JSON.parse(theme);
			let root = document.querySelector(":root");
			for (let color in colors) {
				root.style.setProperty(color, '#' + colors[color]);
			}
		}
		updateAvailableThemes();
	}

	function getTheme(themeName) {
		ThemeChangerService.getTheme(themeName).then((theme) => {
			self.currentTheme = theme.name;

			let root = document.querySelector(":root");
			for (let color in theme.colors) {
				root.style.setProperty(color, '#' + theme.colors[color]);
			}
			window.localStorage.setItem("theme", JSON.stringify(theme.colors));
		});
	}

	function updateAvailableThemes() {
		ThemeChangerService.getAvailableThemes().then((res) => {
			self.availableThemes = res;
		});
	}
}