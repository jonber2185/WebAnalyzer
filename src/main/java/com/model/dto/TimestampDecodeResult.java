package main.java.com.model.dto;

public class TimestampDecodeResult {
	private final boolean success;
	private final String time;
	private final String message;
	
	public TimestampDecodeResult(boolean success, String time, String message) {
		super();
		this.success = success;
		this.time = time;
		this.message = message;
	}

	public boolean isSuccess() { return success; }
	public String getTime() { return time; }
	public String getMessage() { return message; }
}
