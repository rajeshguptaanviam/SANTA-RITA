package com.net.parking.service;

import java.util.Properties;

import com.net.parking.model.User;

public interface EmailService {
	
	public Integer sentEmail(User user, String confirmUrl, Properties emailprop);
}
