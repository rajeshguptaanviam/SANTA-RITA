package com.net.parking.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.net.parking.model.ParkingPlot;
import com.net.parking.model.User;
import com.net.parking.service.ParkingService;
import com.net.parking.service.UserDaoService;


@Controller
public class DashboardController {
	
	private static final Logger logger = Logger.getLogger(DashboardController.class);
	private static Properties properties;
	@Autowired private MessageSource messageSource;
	@Autowired private ParkingService parkingService;
	@Autowired private UserDaoService userDaoService;
	
	
	@RequestMapping(value = "/parking/dashboard" , method = RequestMethod.GET)
	public String dashboard(@ModelAttribute("parkingSpace") ParkingPlot parkingPlot, ModelMap model, HttpSession session){
		String page = "/protected/contador";
		try{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			properties = new Properties();
			properties.load(getClass().getClassLoader().getResourceAsStream("wsapi.properties"));
			User userexist = userDaoService.findUserByEmail(properties.getProperty("find.user.by.userid", ""), auth.getName());
			if (userexist != null){
				
				Integer encendidodelsistema = 0;
				Integer mododeoperacionmanual = 0;
				Integer maxCount = 25;
				parkingPlot = parkingService.fetchParkingcountMax(properties.getProperty("fetch.condator", ""), userexist.getId());
				
				if(parkingPlot != null ){
					if(parkingPlot.getSpace() != null)	maxCount = Integer.parseInt(parkingPlot.getSpace());
					if(parkingPlot.getEncendidoDelSistema() != null)	if(parkingPlot.getEncendidoDelSistema() == 1)	encendidodelsistema = 1;
					if(parkingPlot.getModoDeOperacionManual() != null)	if(parkingPlot.getModoDeOperacionManual() == 1)	mododeoperacionmanual = 1;
				}
				logger.info("maxCount : ===================================>>"+maxCount);
				
				model.addAttribute("parkingcount", maxCount);
				model.addAttribute("encendidodelsistema", encendidodelsistema);
				model.addAttribute("mododeoperacionmanual", mododeoperacionmanual);
				model.addAttribute("welcome", messageSource.getMessage("success.user.login.service", new Object[] { userexist.getName() },"", null));
			}
			
		}catch(Exception exception){	logger.error(exception.getMessage(), exception);	}
		return page;
	}
	
	@RequestMapping(value = "/parking/dashboard", method = RequestMethod.POST)
	public String dashboardSubmit(@Valid ParkingPlot parkingPlot, BindingResult bindingResult, ModelMap model, HttpSession session){
		String page = "/protected/contador";
		try{
			properties = new Properties();
			properties.load(getClass().getClassLoader().getResourceAsStream("wsapi.properties"));
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User userexist = userDaoService.findUserByEmail(properties.getProperty("find.user.by.userid", ""), auth.getName());
			
			parkingPlot.setUserid(userexist.getId());
			
			ParkingPlot parkingPlotexist = parkingService.fetchParkingcountMax(properties.getProperty("fetch.condator", ""), userexist.getId());
			if(parkingPlotexist != null){
				parkingPlot.setModifiedon(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
				parkingPlot.setCreatedon(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
				parkingPlot.setId(parkingPlotexist.getId());
			}else{
				parkingPlot.setCreatedon(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
				parkingPlot.setModifiedon(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
			}
			parkingPlot = parkingService.saveCondator(properties.getProperty("save.condator", ""), parkingPlot);
			page = "redirect:/parking/dashboard";
			
		}catch(Exception exception){	logger.error(exception.getMessage(), exception);	}
		return page;
	}
	
	@RequestMapping(value = "/parking/allocation", method = RequestMethod.GET)
	public String allocation(ModelMap model) {
		return "/protected/allocation";
	}
	
}
