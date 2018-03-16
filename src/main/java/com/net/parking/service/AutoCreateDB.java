package com.net.parking.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.apache.log4j.Logger;

public class AutoCreateDB {
	
	private final static Logger logger = Logger.getLogger(AutoCreateDB.class);
	private String jdbcDriver = "com.mysql.jdbc.Driver";
    private Connection con;
	
	public void getResourcePath(Properties properties) {
		try{
			
			properties.getProperty("spring.datasource.url");
			try {
	            Class.forName(jdbcDriver);
	            con = DriverManager.getConnection(
	            		properties.getProperty("spring.datasource.url"), 
	            		properties.getProperty("spring.datasource.username"), 
	            		properties.getProperty("spring.datasource.password")
	            		);
	            
	            System.out.println("connection : "+con);
	            if(con != null)
	            	logger.info("connection found : ---------------------------->>");
	        } 
	        catch (Exception unknowndatabaseexception) {
	        	logger.info("connection : unknown database exception : ---------------------------->>"+unknowndatabaseexception.getMessage());
	        	Integer result = createDatabase(properties);
	        	if(result != 0)
	        	logger.info("* database created *");
	        } finally{
	        	if(con != null)	con.close();
	        	con = null;
	        }
	        
		}catch(Exception exception){	logger.error(exception.getMessage(), exception);	}
	}
	
	private Integer createDatabase(Properties properties) {
		
		Integer myResult = 0;
		Connection con = null;
		Statement  statement = null;
		
        try {
        	
	        Class.forName(jdbcDriver);
	        String DBLINK = properties.getProperty("spring.datasource.url");
	        String URL = DBLINK.substring(0, DBLINK.lastIndexOf("/")+1);
	        String DB = DBLINK.substring(DBLINK.lastIndexOf("/")+1);
	        con = DriverManager.getConnection((URL)+"?user="
	        					+(properties.getProperty("spring.datasource.username"))+"&password="+(properties.getProperty("spring.datasource.password")));
	        statement = con.createStatement();
	        myResult = statement.executeUpdate("CREATE DATABASE IF NOT EXISTS "+DB);
	        
	    }catch (Exception exception) {	logger.error(exception.getMessage(), exception);	}
        finally{
        	if(con != null)	try {	con.close();	} catch (SQLException e) {}
        	con = null;
        	statement = null;
        }
        return myResult;
    }
}
