package com.net.parking.service;

public interface HttpDatabaseResponse {
	
	public String sendGet(String url) throws Exception;
	public String sendPost(String url, String urlParameters) throws Exception;
}
