package main.java.com.model.core;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

import main.java.com.model.dto.BruteForceResult;
import main.java.com.model.dto.TokenDecodeResult;
import main.java.com.model.dto.TokenEncodeResult;

public class FlaskProcessor implements TokenProcessor {
	// Flask Session Token Format : [payload, timestamp, signature]

	@Override
	public TokenEncodeResult encode(String header, String payload, String secret) {
		try {
			// check data valid # doesn't need header
			if (payload == null || secret == null) 
				throw new IllegalArgumentException("Invalid Flask Session Token format.");
			
			// payload
			payload = payload.replaceAll("\\s", "");
			String encodedPayload = CryptoUtil.base64UrlEncode(payload);
			
			// timestamp
			long currentUnixTime = System.currentTimeMillis() / 1000;
			BigInteger timeBigInt = BigInteger.valueOf(currentUnixTime);
			byte[] newTimestampByte = timeBigInt.toByteArray();
			if (newTimestampByte.length > 1 && newTimestampByte[0] == 0) {
			    byte[] tmp = new byte[newTimestampByte.length - 1];
			    System.arraycopy(newTimestampByte, 1, tmp, 0, tmp.length);
			    newTimestampByte = tmp;
			}
			String encodedTimestamp = CryptoUtil.base64UrlEncode(newTimestampByte);
			
			// signature
			String signature = CryptoUtil.hmacSha256(encodedPayload + "." + encodedTimestamp, secret);
            
            // Flask 토큰 조립
            String token = encodedPayload + "." + encodedTimestamp + "." + signature;
			return new TokenEncodeResult(true, token, "ENCODING SUCCESS!");
		} catch (Exception e) {
			return new TokenEncodeResult(false, null, "Encoding failed: " + e.getMessage());
		}
	}

	@Override
	public TokenDecodeResult decode(String token) {
		try {
			// check token valid
			if (!TokenIdentifier.isFlaskToken(token)) 
				throw new IllegalArgumentException("Invalid Flask Session Token format.");
			
			String[] parts = token.split("\\.");
			String payload = CryptoUtil.base64UrlDecode(parts[0]);
			
			byte[] timestampBytes = CryptoUtil.base64UrlDecodeToBytes(parts[1]);
			long unixTime = 0;
			for (byte b : timestampBytes) {
			    unixTime = (unixTime << 8) | (b & 0xFF);
			}
			long timestampMs = unixTime * 1000;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = sdf.format(new Date(timestampMs));
            
			return new TokenDecodeResult(true, formattedDate, payload, "DECODING SUCCESS!");
		} catch (Exception e) {
            return new TokenDecodeResult(false, null, null, "Decoding failed: " + e.getMessage());
        }
	}

	@Override
	public BruteForceResult bruteForce(String token, String filePath) {
		long startTime = System.currentTimeMillis();
		try {
			// check token valid
			if (!TokenIdentifier.isFlaskToken(token))
				throw new IllegalArgumentException("Invalid Flask Session Token format.");
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
