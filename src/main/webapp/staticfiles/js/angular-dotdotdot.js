angular.module('angular-dotdotdot', [])
	.directive('changdotdotdot', function(){
		return {
			restrict: 'A',
			link: function(scope, element, attributes) {
				scope.$watch(function() {
					element.dotdotdot(
						{
							watch: 'window',
						}
						);
				});
			}
		};
	});