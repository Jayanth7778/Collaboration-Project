/**
 * UserService
 */
app.factory('UserService',function($http){
	var BASE_URL="http://localhost:8090/collaborationmiddleware"
	
	var userService={}
	
	userService.registerUser=function(user){

		console.log(user)
		return $http.post(BASE_URL + "/registerUser",user)
	}
	
	return userService
})