
angular.module('theme').component('themeChanger', {
	controller: ThemeChanger,
	template: '<div>Themes: <span ng-repeat="theme in $ctrl.availableThemes" ng-click="$ctrl.getTheme(theme)"> {{theme}} </span></div>',
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
			getTheme(theme);
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
		})
	}

	function updateAvailableThemes() {
		ThemeChangerService.getAvailableThemes().then((res) => {
			self.availableThemes = res;
		})
	}

}