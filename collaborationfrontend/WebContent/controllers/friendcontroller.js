/**
 * 
 */
app.controller('FriendController',function($scope,$location,FriendService)
		{
			function listOfSuggestedUsers()
			{	
				FriendService.listOfSuggestedUsers().then(function(response)
				{
					$scope.suggestedusers=response.data
				},
				
				function(response)
				{
					if(response.status==401)
						$location.path('/login')
						console.log(response.status)	
				})
		
			}
			
	function listOfPendingRequests()
	{		
		FriendService.listOfPendingRequests().then(function(response)
				{
					$scope.pendingrequests=response.data  
				},
				
				function(response)
				{
					if(response.status==404)
						$location.path('/login')
						console.log(response.status)
				})
	}
	
	$scope.friendRequest=function(toId)
	{
		FriendService.friendRequest(toId).then(function(response)
				{
					alert('Request has beeen sent successfully')
					listOfSuggestedUsers();
					$location.path('/suggesteduserslist')
				},
				
				function(response)
				{
					if(response.status==404)
						$location.path('/login')
						console.log(response.status)
				})
	}
	
	$scope.updatePendingRequest=function(pendingRequest,status)
	{
		console.log(pendingRequest.status)
		pendingRequest.status=status
		
		FriendService.updatePendingRequest(pendingRequest).then(function(response)
				{
					listOfPendingRequests()
					$location.path('/pendingrequests')	
				},
				
				function(response)
				{
					if(response.status==404)
						$location.path('/login')
						console.log(response.status)
			
				})
	}
	
    function listOfFriends()
    {
		FriendService.listOfFriends().then(function(response)
				{
					$scope.friends=response.data ; 
				},
				
				function(response)
				{
					if(response.status==401)
						$location.path('/login')
						console.log(response.status)		
				})
	} 
	
	listOfSuggestedUsers() 
	listOfPendingRequests()
	listOfFriends()
	
})