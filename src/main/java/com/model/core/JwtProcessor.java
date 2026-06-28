package main.java.com.model.core;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.util.stream.Stream;

import main.java.com.model.dto.TokenEncodeResult;
import main.java.com.model.dto.TokenDecodeResult;
import main.java.com.model.dto.BruteForceResult;

public class JwtProcessor implements TokenProcessor {

	@Override
	public TokenEncodeResult encode(String header, String payload, String secret) {
		try {
			// check data valid
			if (header == null || payload == null || secret == null) 
				throw new IllegalArgumentException("Invalid JWT format.");
			
			header = header.replaceAll("\\s", "");
			payload = payload.replaceAll("\\s", "");
			
			String encodedHeader = CryptoUtil.base64UrlEncode(header);
			String encodedPayload = CryptoUtil.base64UrlEncode(payload);
			String signature = CryptoUtil.hmacSha256(encodedHeader + "." + encodedPayload, secret);
			String token = encodedHeader + "." + encodedPayload + "." + signature;
			
			return new TokenEncodeResult(true, token, "ENCODING SUCCESS!");
		} catch (Exception e) {
			return new TokenEncodeResult(false, null, "Encoding failed: " + e.getMessage());
		}
	}

	@Override
	public TokenDecodeResult decode(String token) {
		try {
			// check token valid
			if (!TokenIdentifier.isJWT(token)) 
				throw new IllegalArgumentException("Invalid JWT format.");
			
			String[] parts = token.split("\\.");
			String header = CryptoUtil.base64UrlDecode(parts[0]);
			String payload = CryptoUtil.base64UrlDecode(parts[1]);
			
			return new TokenDecodeResult(true, header, payload, "DECODING SUCCESS!");
		} catch (Exception e) {
            return new TokenDecodeResult(false, null, null, "Decoding failed: " + e.getMessage());
        }
	}

	@Override
	public BruteForceResult bruteForce(String token, String filePath) {
		long startTime = System.currentTimeMillis();
		try {
			// check token valid
			if (!TokenIdentifier.isJWT(token))
				throw new IllegalArgumentException("Invalid JWT format.");
			if (filePath == null) 
				throw new NoSuchFileException("file error");
			
			String[] parts = token.split("\\.");
			String signatureInput = parts[0] + "." + parts[1];
			String originalSignature = parts[2];
			
			// thread stream
			try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
				// compare run
				String foundKey = lines.parallel().filter(tempKey -> {
					try {
						String testSignature = CryptoUtil.hmacSha256(signatureInput, tempKey);
						return testSignature.equals(originalSignature);
					} catch (Exception e) { return false; }
				}).findFirst().orElse(null);
				
				// time 계산
				long duration = System.currentTimeMillis() - startTime;
				
				// 반환
				if (foundKey != null) {
					return new BruteForceResult(true, foundKey, "KEY MATCH!!!", duration);
				} else {
					return new BruteForceResult(false, null, "No Match Result.", duration);
				}
			} catch (InvalidPathException | NoSuchFileException e) {
				// 파일 경로 관련 에러
				throw new Exception("Can not find File.\n\"" + filePath + "\"");
			}
		} catch (Exception e) {
			long duration = System.currentTimeMillis() - startTime;
			return new BruteForceResult(false, null, "BruteForce Failed: " + e.getMessage(), duration);
		} 
	}
}
