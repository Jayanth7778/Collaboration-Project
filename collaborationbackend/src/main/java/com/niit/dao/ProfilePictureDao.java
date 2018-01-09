package com.niit.dao;

import com.niit.model.ProfilePicture;

public interface ProfilePictureDao
{
	
	ProfilePicture getProfilePicture(String username);

	void saveOrUpdateProfilePicture(ProfilePicture profilePicture);
}