package com.niit.dao;

import com.niit.model.User;

public interface UserDao
{
	void registerUser(User user);
	boolean isEmailValid(String email);
	boolean isUsernameValid(String username);

}
