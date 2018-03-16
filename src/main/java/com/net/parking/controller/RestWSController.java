package com.net.parking.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.net.parking.model.ParkingPlot;
import com.net.parking.model.User;
import com.net.parking.repository.ParkingRepository;
import com.net.parking.repository.UserRepository;
import com.net.parking.service.JWTKey;
import com.net.parking.service.UserService;

@RestController
public class RestWSController {
	
	private static final Logger logger = Logger.getLogger(RestWSController.class);
	@Autowired private UserService userService;
	private @Autowired UserRepository userRepository;
	private @Autowired ParkingRepository parkingRepository;
	private @Autowired JWTKey jwtKey;
	
    @RequestMapping(value = "/ws/fetchByUserID", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public User home(@RequestParam(value="userID") String email, @RequestParam("encode") String encode ) {
    	User user = new User();
    	try{
    		if(encode.equalsIgnoreCase(jwtKey.getCompactJws())) user = userRepository.findByEmail(email);
    	}catch(Exception exception){	exception.printStackTrace();	}
        return user;
    }
    
    @RequestMapping(value = "/ws/saveUser", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public User saveUser(@RequestBody User user, @RequestParam("encode") String encode ) {
    	try{
    		if(encode.equalsIgnoreCase(jwtKey.getCompactJws())) userService.saveUser(user);
    	}catch(Exception exception){	exception.printStackTrace();	}
        return user;
    }
    
    @RequestMapping(value = "/ws/saveCondator", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ParkingPlot saveCondator( @RequestBody ParkingPlot parkingPlot, @RequestParam("encode") String encode ){
		ParkingPlot parkingPlotInfo = null;
		try{
			if(encode.equalsIgnoreCase(jwtKey.getCompactJws())) parkingPlotInfo = parkingRepository.save(parkingPlot);
		}catch(Exception exception){	logger.error(exception.getMessage(), exception);	}
		return (parkingPlotInfo);
	}
	
	@RequestMapping(value = "/ws/fetchCondator", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ParkingPlot fetchCondator( @RequestParam("userid") String userid, @RequestParam("encode") String encode ){
		ParkingPlot parkingPlotInfo = null;
		try{
			if(encode.equalsIgnoreCase(jwtKey.getCompactJws()))	parkingPlotInfo = parkingRepository.findByUserid(userid);
		}catch(Exception exception){	logger.error(exception.getMessage(), exception);	}
		return (parkingPlotInfo);
	}
	
	@RequestMapping(value = "/ws/emailconfirm", produces = MediaType.ALL_VALUE, method = RequestMethod.GET)
	public String emailconfirm( @RequestParam("ID") String ID, @RequestParam("status") String status ){
		String msg = "'"+ID+"' user is active";
		try{
			User user = userRepository.findByEmail(ID);
			if(status .equalsIgnoreCase("1"))	user.setStatus(true);
			userRepository.save(user);
		}catch(Exception exception){	msg = "internal server error";	logger.error(exception.getMessage(), exception);	}
		return (msg);
	}
}