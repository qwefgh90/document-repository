/**
 * @author qwefgh90
 */

var app = angular.module('searchServiceModule', []);

app.factory('searchService', function() {
	var search = function(keyword, offset, limitSize) {
		//ajax $http
	};

	return {
		search : search
	};
}); 