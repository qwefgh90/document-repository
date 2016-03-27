/**
 * @author qwefgh90
 */

var app = angular.module('searchModule', ['searchServiceModule', 'angular-dotdotdot']);

app.controller('resultController', ['searchService', '$scope', '$timeout', '$window', '$location',
function(searchService, $timeout, $scope, $window, $location) {
	var self = this;	//http://stackoverflow.com/questions/20279484/how-to-access-the-correct-this-context-inside-a-callback#
	//models..
	this.limitSize = 10;
	this.searchString = '';
	this.page = 1;
	//functionality
	this.go = function(){
		$window.location.href='/staticfiles/search.html?' + 'keyword=' + self.searchString + '&page=' + self.page;
	};
	this.bindSearch = function (){
		//keyword, offset, limitSize
		var promise = searchService.search(self.searchString, 
				(self.page-1) * self.limitSize ,
				self.limitSize);

		promise.then(function(data){
		//	$window.alert(self.searchString + ':done:' + data);
				self.collection = [];
				for (var i = 0; i < data.length ; i++){
					self.collection.push({
											title : data[i].fileName,
											date : data[i].firstAccessTime,
											description : data[i].fileSummary,
											link : data[i].refUrl
										});
				}
		},
				function(errMsg){
		//	$window.alert(self.searchString + ':fail:' + errMsg);

				self.collection = [{
					title : 'fail1',
					date : '200901010',
					description : 'desc',
					link : 'link'
				},
				{
					title : 'fail2',
					date : '200901010',
					description : 'desc',
					link : 'link'
				},{
					title : 'fail3',
					date : '200901010',
					description : 'desc',
					link : 'link'
				}];
		},
				function(notiMsg){
			$window.alert(self.searchString + ':noti:' + notiMsg);
			});
	};
	this.collection = [
//	    {title : 'test',
//		date : '200901010',
//		description : 'dasdfasfd asdfasdf safa sdfasdf asfasfasdfasdf asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfhrthdth asdfasdfasdfhrthdthdrh drhdtrhdrthrdthrtr dsesc',
//		link : 'link'}
	];
	var parseQueryString = function() {
	    var str = decodeURIComponent(window.location.search);
	    var objURL = {};
	    str.replace(
	        new RegExp( "([^?=&]+)(=([^&]*))?", "g" ),
	        function( $0, $1, $2, $3 ){
	            objURL[ $1 ] = $3;
	        }
	    );
	    return objURL;
	};

	
	// processing
	// internal search
	var queryDict = parseQueryString();
	if (queryDict.keyword != undefined) {
		self.searchString = queryDict.keyword;
	}
	if (queryDict.page != undefined) {
		self.page = queryDict.page;
	}
	this.bindSearch();
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
// $scope.collection = 