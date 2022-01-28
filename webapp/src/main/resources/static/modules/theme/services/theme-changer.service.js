
angular.module('theme-module').factory('ThemeChangerService', ThemeChangerServiceFactory)

ThemeChangerServiceFactory.$inject = ['$http']

function ThemeChangerServiceFactory($http) {
	var factory = {
		getAvailableThemes: getAvailableThemes,
		getTheme: getTheme,
		deleteTheme: deleteTheme,
		createTheme: createTheme,
		updateTheme: updateTheme,
	}

	return factory;

	function getAvailableThemes() {
		return $http.get("/themes/list").then((res) => res.data);
	}

	function getTheme(theme) {
		return $http.get(`/themes/${theme}`).then((res) => res.data);
	}

	function deleteTheme(theme) {
		return $http.delete(`/themes/${theme}`).then((res) => {
			console.log(res);
			return res.data
		});
	}

	function createTheme(theme) {
		return $http.post(`/themes`, theme);
	}

	function updateTheme(theme) {
		return $http.put('/themes', theme)
	}
}
