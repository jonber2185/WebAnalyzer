package main.java.com.model.core;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import main.java.com.model.dto.TimestampDecodeResult;
import main.java.com.model.dto.TimestampEncodeResult;

public final class TimestampProcessor {

	public static TimestampEncodeResult encode(String input) {
		Instant instant;
		try {
			instant = Instant.parse(input.trim());
		} catch (Exception e) {
			try {
				LocalDateTime ldt = LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSS]"));
				ZoneId zone = ZoneId.of("UTC");
				instant = ldt.atZone(zone).toInstant();
			} catch (Exception e2) {
				return new TimestampEncodeResult(false, null, null, null, null, "Encoding failed: Invalid time format");
			}
		}
		
		long epochSec = instant.getEpochSecond();
        long epochMilli = instant.toEpochMilli();
		return new TimestampEncodeResult(true, String.valueOf(epochSec), Long.toHexString(epochSec), String.valueOf(epochMilli), Long.toHexString(epochMilli), "ENCODING SUCCESS!");
	}
	
	public static TimestampDecodeResult decode(String timestamp, String zoneId) {
		timestamp = timestamp.trim();
		long value;
		try {
			if (timestamp.toLowerCase().startsWith("0x")) {
				value = Long.parseLong(timestamp.substring(2), 16);
			} else {
				value = Long.parseLong(timestamp);
			}
		} catch (NumberFormatException e) {
			return new TimestampDecodeResult(false, null, "Decoding failed: Invalid timestamp format");
		}
		
		boolean isMillis = Math.abs(value) >= 100_000_000_000L;
        Instant instant = isMillis
                ? Instant.ofEpochMilli(value)
                : Instant.ofEpochSecond(value);
        
        try {
        	return new TimestampDecodeResult(true, format(instant, zoneId), "DECODING SUCCESS!");
        } catch (Exception e) {
        	return new TimestampDecodeResult(false, null, "Decoding failed: Invalid zoneId format");
        }
	}
	
	private static String format(Instant instant, String zoneId) {
		ZonedDateTime zdt = instant.atZone(ZoneId.of(zoneId));
		return zdt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS z"));
	}
}
