
var app = angular.module('coverModule', []);

app.controller('coverController', ['$scope', '$timeout', '$window',
function($timeout, $scope, $window) {
	var self = this;
	this.searchString = '';
	this.go = function(){
		$window.location.href='/staticfiles/search.html?' + 'keyword=' + self.searchString;
	};
}]);


app.directive('focusMe', function($timeout, $parse) {
	//http://stackoverflow.com/questions/14833326/how-to-set-focus-on-input-field
	  return {
	    //scope: true,   // optionally create a child scope
	    link: function(scope, element, attrs) {
	      var model = $parse(attrs.focusMe);
	      scope.$watch(model, function(value) {
	        console.log('value=',value);
	        if(value === true) { 
	          $timeout(function() {
	            element[0].focus(); 
	          });
	        }
	      });
	      // to address @blesh's comment, set attribute value to 'false'
	      // on blur event:
	      element.bind('blur', function() {
	         console.log('blur');
	         scope.$apply(model.assign(scope, false));
	      });
	    }
	  };
	});