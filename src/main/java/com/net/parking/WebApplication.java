package com.net.parking;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.net.parking.service.AutoCreateDB;


@SpringBootApplication
public class WebApplication {
	
	private static Properties properties;
	private final static Logger logger = Logger.getLogger(WebApplication.class);
	
    public static void main(String[] args) throws Exception {
    	
    	properties = new WebApplication().getResourceDatasource();
		new AutoCreateDB().getResourcePath(properties);
        SpringApplication.run(WebApplication.class, args);
    }
    
    private Properties getResourceDatasource() {
		
		try{
			properties = new Properties();
			properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
			
		}catch(Exception exception){	logger.error(exception.getMessage(), exception);	}
		return properties;
	}
}
