package main.java.com.model.dto;

public class TokenDecodeResult {
	private final boolean success;
	private final String header;
	private final String payload;
	private final String message;
	
	public TokenDecodeResult(boolean success, String header, String payload, String message) {
		super();
		this.success = success;
		this.header = header;
		this.payload = payload;
		this.message = message;
	}

	public boolean isSuccess() { return success; }
	public String getHeader() { return header; }
	public String getPayload() { return payload; }
	public String getMessage() { return message; }
}
