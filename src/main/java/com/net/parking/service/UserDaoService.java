package com.net.parking.service;

import com.net.parking.model.User;

public interface UserDaoService {
	
	public User findUserByEmail(String url, String userID) throws Exception;
	public void saveUser(String url, User user) throws Exception;
}
