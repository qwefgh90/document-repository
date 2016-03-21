/**
 * @author qwefgh90
 */

var app = angular.module('searchModule', ['searchServiceModule']);

app.controller('resultController', ['searchService',
function(searchService) {
	this.collection = [{
		title : 'hello',
		date : '200901010',
		description : 'desc',
		link : 'link'
	}, {
		title : 'hello1',
		date : '200901010',
		description : 'longlongtextlonglongtext  onglongtextlonglongtext  onglongtextlonglongtext  onglongtextlonglongtext  onglongtextlonglongtext  onglongtextlonglongtext  onglongtextlonglongtext  onglongtextlonglongtext  onglongtextlonglongtext  onglongtextlonglongtext  onglongtextlonglongtext  onglongtextlonglongtext  onglongtextlonglongtext  onglongtextlonglongtext  onglongtextlonglongtext  onglongtextlonglongtext  onglongtextlonglongtext  onglongtextlonglongtext  onglongtextlonglongtext  onglongtextlonglongtext  onglongtextlonglongtext  ',
		link : 'link'
	}, {
		title : 'hello2',
		date : '200901010',
		description : 'desc2',
		link : 'link'
	}, {
		title : 'hello3',
		date : '200901010',
		description : 'desc3',
		link : 'link'
	}];

}]);

// $scope.collection = 