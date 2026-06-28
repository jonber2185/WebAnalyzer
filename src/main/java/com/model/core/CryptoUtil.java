package main.java.com.model.core;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

public final class CryptoUtil {
	// 객체 생성 방지
	private CryptoUtil() { throw new Error(); }

	// base64 인코더
	public static String base64UrlEncode(String data) {
        return Base64.getUrlEncoder().withoutPadding()
        		.encodeToString(data.getBytes(StandardCharsets.UTF_8));
    }
	public static String base64UrlEncode(byte[] data) {
		return Base64.getUrlEncoder().withoutPadding().encodeToString(data);
	}
	
	// base64 디코더
	public static String base64UrlDecode(String token) {
        byte[] decodedBytes = Base64.getUrlDecoder().decode(token);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
	public static byte[] base64UrlDecodeToBytes(String token) {
		return Base64.getUrlDecoder().decode(token);
	}

	// HMAC. 
    public static String hmacSha256(String data, String secret) throws Exception {
    	Mac sha256Hmac = HMAC_SHA256_THREAD_LOCAL.get();
    	
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256Hmac.init(secretKey);
        
        byte[] signedBytes = sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getUrlEncoder().withoutPadding().encodeToString(signedBytes);
    }
    
    private static final ThreadLocal<Mac> HMAC_SHA256_THREAD_LOCAL = ThreadLocal.withInitial(() -> {
    	try {
    		return Mac.getInstance("HmacSHA256");
    	} catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("HmacSHA256 algorithm not found", e);
        }
    });
}
