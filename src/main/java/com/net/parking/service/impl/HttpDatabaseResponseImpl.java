package com.net.parking.service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.net.parking.service.HttpDatabaseResponse;
import com.net.parking.service.JWTKey;


@Component("httpDatabaseResponse")
public class HttpDatabaseResponseImpl implements HttpDatabaseResponse {
	
	
	@Autowired private JWTKey jwtKey;
	private final String USER_AGENT = "Mozilla/5.0";
	private URL urlobj;
	private StringBuffer response;
	
	
	public String sendGet(String url) throws Exception {
		
		response = null;
		response = new StringBuffer();
		
		if(url.contains("?"))	url =  url + "&encode="+jwtKey.getCompactJws();
		else{	url =  url + "?encode="+jwtKey.getCompactJws();	}
		urlobj = new URL( url );
		
		HttpURLConnection con = (HttpURLConnection) urlobj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setAllowUserInteraction(true);
		
		Integer responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		if(responseCode == 200){
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		}
		return (response.toString());
	}
	
	
	
	public String sendPost(String url, String urlParameters) throws Exception {
		
		response = new StringBuffer();
		
		if(url.contains("?"))	url =  url + "&encode="+jwtKey.getCompactJws();
		else{	url =  url + "?encode="+jwtKey.getCompactJws();	}
		urlobj = new URL( url );
		
		//urlobj = new URL( url + "&encode="+jwtKey.getCompactJws() );
		HttpURLConnection con = (HttpURLConnection) urlobj.openConnection();
		
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Content-Type", "application/json");

		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		Integer responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
		
		if(responseCode == 200){
			BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()));
			String inputLine;
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		}
		return (response.toString());
	}
	
}
