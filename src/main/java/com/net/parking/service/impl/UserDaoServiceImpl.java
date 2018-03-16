package com.net.parking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.net.parking.model.User;
import com.net.parking.service.HttpDatabaseResponse;
import com.net.parking.service.UserDaoService;

@Service("userDaoService")
public class UserDaoServiceImpl implements UserDaoService {
	
	@Autowired private HttpDatabaseResponse httpDatabaseResponse;
	
	
	@Override
	public void saveUser(String url, User user) throws Exception {
		Gson gson = new Gson();
		httpDatabaseResponse.sendPost(url, gson.toJson(user));
	}

	@Override
	public User findUserByEmail(String url, String userID) throws Exception {
		String jsonObject = httpDatabaseResponse.sendGet(url+userID);
		User user = new Gson().fromJson(jsonObject, User.class);
		return user;
	}
	
}
