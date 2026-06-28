package main.java.com.model.dto;

public class TokenEncodeResult {
	private final boolean success;
	private final String token;
	private final String message;
	
	public TokenEncodeResult(boolean success, String token, String message) {
		super();
		this.success = success;
		this.token = token;
		this.message = message;
	}

	public boolean isSuccess() { return success; }
	public String getToken() { return token; }
	public String getMessage() { return message; }
}
