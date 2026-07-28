package main.java.com.controller.timestamp;

import javax.swing.JOptionPane;

import main.java.com.model.core.TimestampProcessor;
import main.java.com.model.dto.TimestampDecodeResult;
import main.java.com.view.panels.timestamp.TimestampDecodePanel;

public class TimestampDecodeController {
	private TimestampDecodePanel view;

	public TimestampDecodeController(TimestampDecodePanel view) {
		this.view = view;
	}

	public void handleDecode() {
		try {
            String timestamp = view.getTimestamp();
            String zoneId = view.getZoneId();

            TimestampDecodeResult result = TimestampProcessor.decode(timestamp, zoneId);
            if (result.isSuccess()) {
            	view.setResult(result.getTime());
            } else throw new Exception(result.getMessage());
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(view, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
        }
	}
}
