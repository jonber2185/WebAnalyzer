package main.java.com.controller.timestamp;

import javax.swing.JOptionPane;

import main.java.com.model.core.TimestampProcessor;
import main.java.com.model.dto.TimestampEncodeResult;
import main.java.com.view.panels.timestamp.TimestampEncodePanel;

public class TimestampEncodeController {
	private TimestampEncodePanel view;

	public TimestampEncodeController(TimestampEncodePanel view) {
		this.view = view;
	}

	public void handleEncode() {
		try {
            String timeText = view.getTimeText();

            TimestampEncodeResult result = TimestampProcessor.encode(timeText);
            if (result.isSuccess()) {
            	view.setResult(result.getTimestampDec(), result.getTimestampHex(),
            			result.getTimestampMillDec(), result.getTimestampMillHex());
            } else throw new Exception(result.getMessage());
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(view, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
        }
	}
}
