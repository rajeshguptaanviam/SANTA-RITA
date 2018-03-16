package com.net.parking.service.impl;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.net.parking.model.ParkingPlot;
import com.net.parking.service.HttpDatabaseResponse;
import com.net.parking.service.ParkingService;

@Service("parkingService")
public class ParkingServiceImpl implements ParkingService {
	
	@Autowired HttpDatabaseResponse httpDatabaseResponse;
	
	@Override
	public ParkingPlot fetchParkingcountMax(String url, String Id) throws Exception {
		String jsonObject = httpDatabaseResponse.sendGet( url + Id );
		ParkingPlot parkingPlot = new Gson().fromJson(jsonObject, ParkingPlot.class);
		return parkingPlot;
	}

	@Override
	public ParkingPlot saveCondator(String url, ParkingPlot parkingPlot) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(parkingPlot);
		jsonInString = httpDatabaseResponse.sendPost(url, jsonInString);
		parkingPlot = new Gson().fromJson(jsonInString, ParkingPlot.class);
		return parkingPlot;
	}

}
