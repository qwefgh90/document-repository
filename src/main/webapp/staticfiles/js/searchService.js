/**
 * @author qwefgh90
 */

var app = angular.module('searchServiceModule', ['searchServiceModule']);

app.factory('searchService', ['$http', '$q', function($http, $q) {
	var search = function(keyword, offset, limitSize) {
		var deferred = $q.defer();
		
		// ajax $http
		var headers = {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		};
		var params = {
			'keyword' : keyword,
			'offset' : offset,
			'limitSize' : limitSize,
			'mode' : 'DefaultRankAppliedToEachKeyword'
		};
		var config = {
			'params' : params,
			'headers' : headers
		};

		$http.get(url = '/search', config = config).then(function(response) {
			if (response.status == 200) {
				deferred.resolve(response.data);
			} else {
				deferred.reject('search fail');
			}
		}, function(response) {
			deferred.reject(response.data);
		}, function(response) {
			deferred.reject(response.data);
		});
		return deferred.promise;
		// then(successCallback, errorCallback, notifyCallback)
	};

	return {
		search : search
	};
} ]);