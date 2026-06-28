package main.java.com.model.dto;

public class BruteForceResult {
	private final boolean success;
	private final String foundKey;
	private final String message;
	private final long time;

	public BruteForceResult(boolean success, String foundKey, String message, long time) {
		super();
		this.success = success;
		this.foundKey = foundKey;
		this.message = message;
		this.time = time;
	}

	public boolean isSuccess() { return success; }
	public String getFoundKey() { return foundKey; }
	public String getMessage() { return message; }
	public long getTime() { return time; }
}
