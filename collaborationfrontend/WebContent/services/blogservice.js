/**
 * BlogService
 */
app.factory('BlogService',function($http)
		{
			var blogService={}
			var BASE_URL="http://localhost:8090/collaborationmiddleware"
				
				blogService.saveBlog=function(blog)
				{
					return $http.post(BASE_URL + "/saveblog",blog)
				}
			
				blogService.getBlogsApproved=function()
				{
					return $http.get(BASE_URL + "/getblogs/"+1)
				}
				
				blogService.getBlogsWaitingForApproved=function()
				{
					return $http.get(BASE_URL + "/getblogs/"+0)
				}
			
				blogService.getBlogPost=function(id)
				{
					return $http.get(BASE_URL + "/getblog/"+id)
				}
				
			return blogService;
		})