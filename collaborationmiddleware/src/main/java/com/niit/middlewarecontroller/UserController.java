package com.niit.middlewarecontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.dao.UserDao;
import com.niit.model.ErrorClazz;
import com.niit.model.User;

@Controller
public class UserController
{
	@Autowired
	private UserDao userDao;
	
	public UserController()
	{
		System.out.println("UserController is instantiated successfully");
	}
	
	@RequestMapping(value="/registerUser",method=RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user)
	{
		try
		{
			if(!userDao.isUsernameValid(user.getUsername()))
			{
				ErrorClazz error = new ErrorClazz(2,"Username already exists select different username");
				return new ResponseEntity<ErrorClazz>(error,HttpStatus.CONFLICT);
			}
			
			if(!userDao.isEmailValid(user.getEmail()))
			{
				ErrorClazz error = new ErrorClazz(3,"Email Id already registered Use an alternate Email_Id");
				return new ResponseEntity<ErrorClazz>(error,HttpStatus.CONFLICT);

			}
			userDao.registerUser(user);		
		}
		catch(Exception e)
		{
			ErrorClazz error = new ErrorClazz(1,"Error registering the user");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
}
