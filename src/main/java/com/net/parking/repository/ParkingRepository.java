package com.net.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.net.parking.model.ParkingPlot;

@Repository("parkingRepository")
public interface ParkingRepository extends JpaRepository<ParkingPlot, String> {
	
	public ParkingPlot findById(String id) throws Exception;
	public ParkingPlot findByUserid(String id) throws Exception;
}
