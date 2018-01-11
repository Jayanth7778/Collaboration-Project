/**
 * Friend Service
 */
app.factory('FriendService',function($http)
		{
			var  friendService={}
	
			friendService.listOfSuggestedUsers=function()
			{
				return  $http.get("http://localhost:8090/collaborationmiddleware/getsuggestedusers")	 
			}
			
			friendService.friendRequest=function(toId)
			{
				return  $http.post("http://localhost:8090/collaborationmiddleware/friendrequest/"+toId)
			}
			
			friendService.listOfPendingRequests=function()
			{
				return  $http.get("http://localhost:8090/collaborationmiddleware/getpendingrequests")	 
			}
	
			friendService.getUserDetails=function(fromId)
			{
				return  $http.get("http://localhost:8090/collaborationmiddleware/getuserdetails/"+fromId)
			}
	
			friendService.updatePendingRequest=function(pendingRequest)
			{
				return  $http.put("http://localhost:8090/collaborationmiddleware/updatependingrequest",pendingRequest)
			}
	
			friendService.listOfFriends=function()
			{
				return  $http.get("http://localhost:8090/collaborationmiddleware/listoffriends")
			}
	
	return friendService;
})