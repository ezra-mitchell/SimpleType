angular.module('theme').controller('ThemeEditorController', ThemeEditor);


ThemeEditor.$inject = ['ThemeChangerService']

function ThemeEditor(ThemeChangerService) {
	var self = this;

	self.theme = { name: '', id: undefined, colors: {} };
	self.availableThemes = [];
	self.error = '';


	self.getTheme = function(themeName) {
		ThemeChangerService.getTheme(themeName).then((theme) => {
			self.theme = theme;
		})
	}


	self.updateAvailableThemes = function() {
		ThemeChangerService.getAvailableThemes().then((res) => {
			self.availableThemes = res;
		})
	}


	self.updateTheme = function() {
		console.log(self.theme);
		if (!self.theme.id) return;

		ThemeChangerService.updateTheme(self.theme).then((res) => {
			console.log(res);
		})
	}

	self.createTheme = function() {
		console.log("here");
		self.theme.id = undefined;
		ThemeChangerService.createTheme(self.theme).then((res) => {
			console.log(res);
		})
	}

	self.deleteTheme = function() {
		if (!self.theme.name) return;
		ThemeChangerService.deleteTheme(self.theme.name).then((res) => {
			console.log(res);
		})
	}

	self.stubSubmit = function(e) {
		e.preventDefault();
	}

	function init() {
		self.updateAvailableThemes();
	}

}