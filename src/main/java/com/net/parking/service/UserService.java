package com.net.parking.service;

import com.net.parking.model.User;

public interface UserService {
	public void saveUser(User user) throws Exception;
	public User findUserByEmail(String email) throws Exception;
	public User userDetails(String property, String useremail) throws Exception;
}
