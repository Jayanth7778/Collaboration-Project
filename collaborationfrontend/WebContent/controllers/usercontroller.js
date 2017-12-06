/**
 * UserController
 */
app.controller('UserController',function($scope,UserService,$location){

	
	$scope.registerUser=function(){
		console.log($scope.user)
		UserService.registerUser($scope.user)
		.then(function(response){
			$location.path('/home')
		},
		function(response)
		{
			console.log(response.data)
			console.log(response.status)
			$scope.error=response.data  //ErrorClazz object in JSON
		})
	}
})