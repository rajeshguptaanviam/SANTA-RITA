package com.net.parking.service;

import java.security.Key;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Component
public class JWTKey {
	
	private Key key;
	private String compactJws;
	
	public JWTKey() {
		
	}
	
	public void config(){
		this.key = MacProvider.generateKey();
		this.compactJws = Jwts.builder() .setSubject("SANTA-RITA") .compressWith(CompressionCodecs.DEFLATE) .signWith(SignatureAlgorithm.HS512, key)
		.compact();
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getCompactJws() {
		return compactJws;
	}

	public void setCompactJws(String compactJws) {
		this.compactJws = compactJws;
	}
}
