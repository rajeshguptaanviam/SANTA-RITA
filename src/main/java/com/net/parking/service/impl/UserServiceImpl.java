package com.net.parking.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.net.parking.model.Role;
import com.net.parking.model.User;
import com.net.parking.repository.RoleRepository;
import com.net.parking.repository.UserRepository;
import com.net.parking.service.HttpDatabaseResponse;
import com.net.parking.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	@Autowired private UserRepository userRepository;
	@Autowired private RoleRepository roleRepository;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired HttpDatabaseResponse httpDatabaseResponse;
	
	@Override
	public void saveUser(User user) throws Exception {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setStatus(true);
        Role userRole = roleRepository.findByRole("ADMIN");
        
        if(userRole != null){
	        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
	        userRepository.save(user);
        }else{
        	userRole = new Role("ADMIN", (new Date())+"", "SYSTEM", (new Date())+"", "SYSTEM");
    	    user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
    	    userRepository.save(user);
        }
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public User findUserByEmail(String email) throws Exception {
		return userRepository.findByEmail(email);
	}

	@Override
	public User userDetails(String url, String email) throws Exception {
		
		String jsonObject = httpDatabaseResponse.sendGet( url + email );
		logger.info(" jsonObject :-------------------------------------------->>"+(jsonObject));
		User user = new Gson().fromJson(jsonObject, User.class);
		return user;
	}
	
}
