package main.java.com.model.core;

import main.java.com.model.dto.TokenEncodeResult;
import main.java.com.model.dto.TokenDecodeResult;
import main.java.com.model.dto.BruteForceResult;

public interface TokenProcessor {
    
	public TokenEncodeResult encode(String header, String payload, String secret);
	// To use Flask Processor, header should be null;
    
	public TokenDecodeResult decode(String token);
    
	public BruteForceResult bruteForce(String token, String filePath);
}
