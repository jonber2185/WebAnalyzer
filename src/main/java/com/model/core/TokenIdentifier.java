package main.java.com.model.core;

import java.util.Base64;

import main.java.com.model.type.TokenType;

public final class TokenIdentifier {
	
	public static boolean isJWT(String token) {
		if (token == null) return false;
		
		String[] parts = token.split("\\.");
		if (parts.length != 3) return false;
		
		try {
			// 토큰 해더 추출
			String decodedHeader = 
					new String(Base64.getUrlDecoder().decode(parts[0]));
			
			// JWT Format : {"alg":?, "typ":"JWT", etc..}
			if (
				decodedHeader.contains("\"alg\"") &&
				decodedHeader.contains("\"typ\":\"JWT\"")
			) return true;
		} catch (Exception e) { }
		return false;
		// delete parts
	}
	
	public static boolean isFlaskToken(String token) { // 추정
		String[] parts = token.split("\\.");
		if (parts.length < 2 || parts.length > 3) return false;
		
		try {
			// 토큰 해더 추출
			String decodedHeader = new String(Base64.getUrlDecoder().decode(parts[0]));
			
			if (decodedHeader.startsWith("{")) return true;
		} catch (Exception e) { }
		
		return false;
	}

	public static TokenType identify(String token) {
		// token part 분리
		if (isJWT(token)) return TokenType.JWT;
		else if (isFlaskToken(token)) return TokenType.FLASK;
		else return TokenType.UNKNOWN;
	}
}
