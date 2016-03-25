/**
 * @author qwefgh90
 */

var app = angular.module('searchModule', ['searchServiceModule','angular-dotdotdot']);

app.controller('resultController', ['searchService', '$scope', '$timeout', '$window',
function(searchService, $timeout, $scope, $window) {

	var self = this;	//http://stackoverflow.com/questions/20279484/how-to-access-the-correct-this-context-inside-a-callback#
	this.limitSize = 10;
	this.page = 1;
	this.bindSearch = function (){
		//keyword, offset, limitSize
		var promise = searchService.search(self.searchString, 
				(self.page-1) * self.limitSize ,
				self.limitSize);

		promise.then(function(data){$window.alert(self.searchString + ':done:' + data);
				self.collection = [{
					title : 'success1',
					date : '200901010',
					description : 'desc',
					link : 'link'
				},
				{
					title : 'success2',
					date : '200901010',
					description : 'desc',
					link : 'link'
				},
				{
					title : 'success3',
					date : '200901010',
					description : 'desc',
					link : 'link'
				}];
		},
				function(errMsg){$window.alert(self.searchString + ':fail:' + errMsg);

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
				function(notiMsg){$window.alert(self.searchString + ':noti:' + notiMsg);});
	};
	this.collection = [{
		title : 'test',
		date : '200901010',
		description : 'dasdfasfd asdfasdf safa sdfasdf asfasfasdfasdf asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfhrthdth asdfasdfasdfhrthdthdrh drhdtrhdrthrdthrtr dsesc',
		link : 'link'
	},
	{
		title : 'test',
		date : '200901010',
		description : 'dasdfasfd asdfassadfasdfsadfasddf safa sdfasdf dfasdf asdfasdfasdfasdfasdfasddfasdf asdfasdfasdfasdfasdfasddfasdf asdfasdfasdfasdfasdfasddfasdf asdfasdfasdfasdfasdfasddfasdf asdfasdfasdfasdfasdfasddfasdf asdfasdfasdfasdfasdfasddfasdf asdfasdfasdfasdfasdfasdasfasfasdfasdf asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfhrthdth asdfasdfasdfhrthdthdrh drhdtrhdrthrdthrtr dsesc',
		link : 'link'
	},
	{
		title : 'test',
		date : '200901010',
		description : 'dasdfasfd asdfasdf safa sdfasdf asfasfasdfas sdfasdf asfasf sdfasdf asfasf sdfasdf asfasf sdfasdf asfasf sdfasdf asfasf sdfasdf asfasf sdfasdf asfasf sdfasdf asfasf sdfasdf asfasf sdfasdf asfasf sdfasdf asfasf sdfasdf asfasf sdfasdf asfasf sdfasdf asfasf sdfasdf asfasf sdfasdf asfasf sdfasdf asfasfdf asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfhrthdth asdfasdfasdfhrthdthdrh drhdtrhdrthrdthrtr dsesc',
		link : 'link'
	},
	{
		title : 'test',
		date : '200901010',
		description : 'dsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsd fdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdfdsafsdf',
		link : 'link'
	}];
}]);

// $scope.collection = 