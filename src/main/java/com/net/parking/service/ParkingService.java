package com.net.parking.service;

import com.net.parking.model.ParkingPlot;

public interface ParkingService {

	public ParkingPlot fetchParkingcountMax(String url, String Id) throws Exception;
	public ParkingPlot saveCondator(String url, ParkingPlot parkingPlot) throws Exception;
}
