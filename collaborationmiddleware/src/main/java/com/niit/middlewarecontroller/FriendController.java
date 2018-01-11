package com.niit.middlewarecontroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.dao.FriendDao;
import com.niit.dao.UserDao;
import com.niit.model.ErrorClazz;
import com.niit.model.Friend;
import com.niit.model.User;

@Controller
public class FriendController 
{
	@Autowired
	public FriendDao friendDao;

	@Autowired
	public UserDao userDao;

	@RequestMapping(value="/getsuggestedusers",method=RequestMethod.GET)
	public ResponseEntity<?>getListOfSuggestedUsers(HttpSession session)
	{
		String username=(String)session.getAttribute("username");
		if(username==null)
		{
			ErrorClazz error = new ErrorClazz(5,"Unauthorized Access");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
	
		List<User> suggestedUsers=friendDao.getListOfSuggestedUsers(username);
		return new ResponseEntity<List<User>>(suggestedUsers,HttpStatus.OK);
	}
	
	@RequestMapping(value="/friendrequest/{toId}",method=RequestMethod.POST)
	public ResponseEntity<?> friendRequest(@PathVariable String toId, HttpSession session)
	{
		String username=(String)session.getAttribute("username");
		if(username==null)
		{
			ErrorClazz error = new ErrorClazz(5,"Unauthorized Access");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		
		friendDao.addFriendRequest(username, toId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value="/getpendingrequests",method=RequestMethod.GET)
	public ResponseEntity<?> getPendingRequests(HttpSession session)
	{
		String username=(String)session.getAttribute("username");
		if(username==null)
		{
			ErrorClazz error = new ErrorClazz(5,"Unauthorized Access");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		
		List<Friend> pendingRequests=friendDao.getPendingRequests(username);
		return new ResponseEntity<List<Friend>>(pendingRequests,HttpStatus.OK);
	}

	@RequestMapping(value="/getuserdetails/{fromId}",method=RequestMethod.GET)
	public ResponseEntity<?> getUserDetails(@PathVariable String fromId,HttpSession session)
	{	
		String username=(String)session.getAttribute("username");
		if(username==null)
		{
			ErrorClazz error = new ErrorClazz(5,"Unauthorized Access");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
	
		User user=userDao.getUserByUsername(fromId);
		return new ResponseEntity<User>(user,HttpStatus.OK);
}
	
	@RequestMapping(value="/updatependingrequest",method=RequestMethod.PUT)
	public ResponseEntity<?>updatePendingRequest(@RequestBody Friend pendingRequest, HttpSession session)
	{
		String username=(String)session.getAttribute("username");
		if(username==null)
		{
			ErrorClazz error = new ErrorClazz(5,"Unauthorized Access");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
	
		friendDao.updatePendingRequest(pendingRequest);
		return new ResponseEntity<Friend>(pendingRequest,HttpStatus.OK);
	}
	
	@RequestMapping(value="/listoffriends",method=RequestMethod.GET)
	public ResponseEntity<?>listOfFriends(HttpSession session)
	{
		String username=(String)session.getAttribute("username");
		if(username==null)
		{
			ErrorClazz error = new ErrorClazz(5,"Unauthorized Access");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		
		List<Friend> friends=friendDao.listOfFriends(username); 
		return new ResponseEntity<List<Friend>>(friends,HttpStatus.OK);
	
	}

}