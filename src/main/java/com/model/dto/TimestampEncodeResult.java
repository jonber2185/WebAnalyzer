package main.java.com.model.dto;

public class TimestampEncodeResult {
	private final boolean success;
	private final String timestampDec;
	private final String timestampHex;
	private final String timestampMillDec;
	private final String timestampMillHex;
	private final String message;
	
	public TimestampEncodeResult(boolean success, String timestampDec, String timestampHex, String timestampMillDec,
			String timestampMillHex, String message) {
		super();
		this.success = success;
		this.timestampDec = timestampDec;
		this.timestampHex = timestampHex;
		this.timestampMillDec = timestampMillDec;
		this.timestampMillHex = timestampMillHex;
		this.message = message;
	}
	
	public boolean isSuccess() { return success; }
	public String getTimestampDec() { return timestampDec; }
	public String getTimestampHex() { return timestampHex; }
	public String getTimestampMillDec() { return timestampMillDec; }
	public String getTimestampMillHex() { return timestampMillHex; }
	public String getMessage() { return message; }
}
