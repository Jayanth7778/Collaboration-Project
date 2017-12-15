/**
 * BlogPostDetailsController
 */
app.controller('BlogPostDetailsController',function($scope,$location,$routeParams,BlogService)
		{
			var id=$routeParams.id
			
			BlogService.getBlogPost(id).then(function(response)
					{
						$scope.blogPost=response.data
					},
					function(response)
					{
						if(response.status==401)
						{
							$location.path('/login')
						}
					})
		})